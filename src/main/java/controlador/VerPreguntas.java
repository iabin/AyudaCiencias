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
 * @author Iabin
 */
@ManagedBean
@RequestScoped
/**
 * Clase que modela el caso de uso de ver todas las preguntsas
 */
public class VerPreguntas {
    //Todas las preguntas
    private List<Pregunta> preguntas;
    
   
    
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    
   
    /**
     * Metodo que se ejecuta siempre al construir y genera una lista con todas las preguntas
     */
    @PostConstruct
    public void ver() {
        PreguntaDAO lib = new PreguntaDAO();
        preguntas = lib.preguntas();
}
}
