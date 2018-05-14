/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.*;

/**
 *
 * @author Alan
 */
//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@ViewScoped
public class BorrarUsuario {
    private String usuario;
    UsuarioDAO ud=new UsuarioDAO();
    RespuestaDAO rd=new RespuestaDAO();
    PreguntaDAO pd=new PreguntaDAO();
    
    public void setUsuario(String s){
        usuario=s;
    }
    
    public String getUsuario(){
        return usuario;
    }
            
    public String borrarUsuario(String s){    
        Set<Pregunta> s1=ud.buscaUsuario(Integer.parseInt(s)).getPreguntas();
        Set<Respuesta> s2=ud.buscaUsuario(Integer.parseInt(s)).getRespuestas();
        
        //System.out.println();
        for(Respuesta r:s2){
            
            //RespuestaDAO rd=new RespuestaDAO();
            rd.eliminaRespuesta(r);
          //  System.out.println(r.getContenido());
        } 
        
        for(Pregunta r:s1){
            
            //RespuestaDAO rd=new RespuestaDAO();
            pd.eliminaPregunta(r);
          //  System.out.println(r.getContenido());
        } 
        
        
        //this.u=ud.buscaUsuario(u.getIdusuario());
        ud.eliminaUsuario(ud.buscaUsuario(Integer.parseInt(s)));
        
        return "index.xhtml?faces-redirect=true";
    }
}
