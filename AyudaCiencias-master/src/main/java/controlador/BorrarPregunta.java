/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.PreguntaDAO;
import modelo.RespuestaDAO;
/**
 *
 * @author Alan
 */
//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@ViewScoped
public class BorrarPregunta {
   
    private String pregunta;
    //private List<Respuesta> lr;
    RespuestaDAO rd=new RespuestaDAO();
    PreguntaDAO pd=new PreguntaDAO();
    
    public void setPregunta(String p){
        pregunta=p;
    }
    
    public String getUsuario(){
        return pregunta;
    }
    
    public String borrarPregunta(String p){
        //System.out.println("Aqui");
        
       Set<Respuesta> s=pd.buscaPregunta(Integer.parseInt(p)).getRespuestas();
        //Set<Respuesta> s=p.getRespuestas();
        System.out.println( s.size());
        
        for(Respuesta r:s){
            
            //RespuestaDAO rd=new RespuestaDAO();
            rd.eliminaRespuesta(r);
          //  System.out.println(r.getContenido());
        } 
        //this.p=pd.buscaPregunta(Integer.parseInt(p));
        
        pd.eliminaPregunta(pd.buscaPregunta(Integer.parseInt(p)));
        
         return "index.xhtml?faces-redirect=true";
    }
}
