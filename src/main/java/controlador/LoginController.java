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
 *
 * @author jonathan
 */

@ManagedBean
@SessionScoped
public class LoginController  {
    
    
    private String username;
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
            
            context.getExternalContext().getSessionMap().put("user", user);
            context.addMessage(null, new FacesMessage("Inicio Exitoso"));
            username = null;
            password = null;
            return "/index.xhtml?faces-redirect=true";
        
        }
    }

    public String  logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
}