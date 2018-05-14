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
public class VerPreguntas {
    
    private List<Pregunta> preguntas;
    
   
    
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    
   
    @PostConstruct
    public void ver() {
        PreguntaDAO lib = new PreguntaDAO();
        preguntas = lib.preguntas();
}
}
