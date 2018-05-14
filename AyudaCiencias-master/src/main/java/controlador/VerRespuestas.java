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

    public void setId(String id) {
        this.id = id;
        PreguntaDAO pdao = new PreguntaDAO();
        this.pregunta = pdao.buscaPregunta(Integer.parseInt(id));
        this.respuestas = new ArrayList<Respuesta>(this.pregunta.getRespuestas());
    }

    public String getId() {
        return id;
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
