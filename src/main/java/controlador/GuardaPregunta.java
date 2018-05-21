/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.*;
import static org.primefaces.component.focus.Focus.PropertyKeys.context;

/**
 *
 */
//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@ViewScoped
/**
 * Clase que modela el caso de usa de guardar pregunta
 */
public class GuardaPregunta {
    
   
    private String contenido;
    UsuarioDAO ud=new UsuarioDAO();
    
    

    public String getContenido() {
        return contenido;
    }

    
    public void setContenido(String c) {
        this.contenido = c;
    }
    
    /**
     * peticion que guarda un proyecto 
     */
    public String guardaPregunta(){
        Pregunta p = new Pregunta();
        Usuario u=ud.buscaUsuario(34);
        p.setContenido(contenido);
        p.setUsuario(u);
        PreguntaDAO pd = new PreguntaDAO();
        pd.nuevaPregunta(p);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Todo chidori"));
        
        
        return "index.xhtml?faces-redirect=true";
    }
     
}