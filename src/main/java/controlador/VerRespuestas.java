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
 * @author jonathan
 */
@ManagedBean
@SessionScoped
public class VerRespuestas {
    private Pregunta pregunta;
    private List<Respuesta> respuestas;
    private String id;
    private String msg;

    public void setId(String id) {
        this.id = id;
        PreguntaDAO pdao = new PreguntaDAO();
        try{
            this.pregunta = pdao.buscaPregunta(Integer.parseInt(id));
            this.respuestas = new ArrayList<Respuesta>(this.pregunta.getRespuestas());
        }catch(Exception e){
            
        }
        
        
    }
    
    public boolean existe(){
       PreguntaDAO pdao = new PreguntaDAO();
        if(this.pregunta!=null){
            this.msg = this.pregunta.getIdpregunta()+"";
            return pdao.buscaPregunta(this.pregunta.getIdpregunta())!=null;
            
            /*if(pdao.buscaPregunta(Integer.parseInt(id))!=null){
                this.msg="Hola========================";
                return true;
            }else{
                this.msg="No existe";
                return false;
            }*/
        }else{
            this.msg="   Error 404: No existe la pagina que estas buscando ";
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
