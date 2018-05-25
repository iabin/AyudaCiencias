package controlador;

import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    
      private static final String us= "dragonsoftis";
    private static final String pass = "dragonsoft";
    
    
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
    
   public void enviarCorreo(String email, String asunto, String mensaje){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(us, pass);
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(us));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(asunto);
            message.setText(mensaje);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
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
            
            //Que pertenezca a ciencias
            if(!correo.contains("@ciencias")){
                 FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Necesita ser un correo de ciencias"));
                return "";
            }
           
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            for(Usuario o:usuarioDAO.usuarios()){
                if (o.getCorreo().equalsIgnoreCase(correo)){
                     FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Ese correo ya fue registrado"));
                    return "";
                }
            }
          
                
            Boolean r = usuarioDAO.nuevoUsuario(usuario);
            if(!r){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Agregado incorrectamente"));
                return "ingresar.xhtml?faces-redirect=true";
            }
            enviarCorreo(correo, "confirma tu registro", "intenta iniciar sesion para comprobar el registro");
        
    return "index.xhtml?faces-redirect=true"; 
    }

}