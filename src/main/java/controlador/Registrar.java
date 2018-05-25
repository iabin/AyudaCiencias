package controlador;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;


/**
 * Clase que modela el caso de uso registrar
 * @author Uzziel
 */
@ManagedBean
@RequestScoped
public class Registrar {
    private String nombre;
    private String correo;
    private String contrasena;
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
   
 
    /**
     * Metodo que hace persistente a un usario en la base de datos
     * @return String con direccion de retorno
     */
    public String agregarUsuario() {
       
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setContrasena(contrasena);
           
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Boolean r = usuarioDAO.nuevoUsuario(usuario);
            if(!r){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Agregado incorrectamente"));
                return "ingresar.xhtml?faces-redirect=true";
            }
        
    return "index.xhtml?faces-redirect=true"; 
    }

}