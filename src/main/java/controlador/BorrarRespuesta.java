/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import modelo.PreguntaDAO;
import modelo.Respuesta;
import modelo.RespuestaDAO;
/**
 *
 * @author Alan
 */
//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@RequestScoped
public class BorrarRespuesta {
    private String respuesta;
    RespuestaDAO rd=new RespuestaDAO();
    PreguntaDAO pd=new PreguntaDAO();
    
    //public BorrarRespuesta(int id){
        //this.r=rd.buscaRespuesta(id);
      //  this.r=r;
        
    //}

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String r) {
        this.respuesta = r;
    }
    
     
    /**
     * Metodo que borra una respuesta dada un id 
     * @param r string que representa el id
     * @return string con la direccion de retorno
     */
    public String borrarRespuesta(String r){
        Respuesta res = rd.buscaRespuesta(Integer.parseInt(r));
        rd.eliminaRespuesta(res);
        return "index.xhtml?faces-redirect=true";
    }
}
