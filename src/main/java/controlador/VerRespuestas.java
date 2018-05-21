/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import modelo.*;


/**
 *
 * @author Iabin
 */
@ManagedBean
@SessionScoped
/**
 * Clase que modela el caso de uso de ver respuestas
 */
public class VerRespuestas {
    private Pregunta pregunta;//Pregunta asociada
    private List<Respuesta> respuestas;//Respuestas de la pregunta
    private String id;//Id de la pregunta
    private String msg; //Mensaje a mostrar

    public void setId(String id) {
        this.id = id;
        PreguntaDAO pdao = new PreguntaDAO();
        try{
            this.pregunta = pdao.buscaPregunta(Integer.parseInt(id));
            this.respuestas = new ArrayList<Respuesta>(this.pregunta.getRespuestas());
        }catch(Exception e){
            
        }
        
        
    }
    
    /**
     * Metodo que verifica si existe una pregunta
     * @return false si no existe, true en otro caso
     */
    public boolean existe(){
       PreguntaDAO pdao = new PreguntaDAO();
        if(this.pregunta!=null){
            this.msg = this.pregunta.getIdpregunta()+"";
            return pdao.buscaPregunta(this.pregunta.getIdpregunta())!=null;
        }else{
            this.msg="   Error 404: No existe la pregunta que estás buscando";
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }
    
   
    
    public List<Respuesta> getRespuestas() {
        return respuestas;
    }
    
   
}
