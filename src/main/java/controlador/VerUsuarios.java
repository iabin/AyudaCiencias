/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.*;


/**
 *Clase que modela el caso de uso ver usuarios
 * @author Alan
 */
@ManagedBean
@RequestScoped
public class VerUsuarios {
    //La lista de usuarios
    private List<Usuario> usuarios;
    
   
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
   //Post constructor jala todos los usaurios de la base de datos
    @PostConstruct
    public void ver() {
        UsuarioDAO lib = new UsuarioDAO();
        usuarios = lib.usuarios();
}
}
