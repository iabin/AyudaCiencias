/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.*;

/**
 *Clase que modela la clase para ingresar y salir de session
 * @author Uzziel
 */

@ManagedBean
@SessionScoped
public class LoginController  {
    
    //Usuario
    private String username;
    //contrasena
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    /**
     * Busca en todos los usuarios tu nombre y contrasena
     * @return direccion de retorno
     */
    public String confirmar() {
        UsuarioDAO us = new UsuarioDAO();
        Usuario user = null;
        for(Usuario u: us.usuarios()){
            if(u.getCorreo().equalsIgnoreCase(this.username)&&u.getContrasena().equalsIgnoreCase(password)){
                user = u;
                break;
            }
        }
        user.setConfirmado(true);
        us.actualizaUsuario(user);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("No se pudo hacer logín,Usuario no encontrado"));
            username = null;
            password = null;
            return "";
        } else{
            
            context.getExternalContext().getSessionMap().put("user", user);
            context.addMessage(null, new FacesMessage("Inicio Exitoso"));
            username = null;
            password = null;
            return "/index.xhtml?faces-redirect=true";
        
        }
    }
    
    /**
     * Busca en todos los usuarios tu nombre y contrasena
     * @return direccion de retorno
     */
    public String login() {
        UsuarioDAO us = new UsuarioDAO();
        Usuario user = null;
        for(Usuario u: us.usuarios()){
            if(u.getCorreo().equalsIgnoreCase(this.username)&&u.getContrasena().equalsIgnoreCase(password)){
                user = u;
                break;
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("No se pudo hacer logín,Usuario no encontrado"));
            username = null;
            password = null;
            return "";
        } else{
            if(user.isConfirmado()==false){
            context.addMessage(null, new FacesMessage("No se ha verificado su correo"));
            username = null;
            password = null;
            return "";
            }
            
            context.getExternalContext().getSessionMap().put("user", user);
            context.addMessage(null, new FacesMessage("Inicio Exitoso"));
            username = null;
            password = null;
            return "/index.xhtml?faces-redirect=true";
        
        }
    }
    /**
     * Sale de la session
     * @return string de retorno
     */
    public String  logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
}