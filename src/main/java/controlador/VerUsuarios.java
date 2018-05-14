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
 *
 * @author jonathan
 */
@ManagedBean
@RequestScoped
public class VerUsuarios {
    
    private List<Usuario> usuarios;
    
   
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
   
    @PostConstruct
    public void ver() {
        UsuarioDAO lib = new UsuarioDAO();
        usuarios = lib.usuarios();
}
}
