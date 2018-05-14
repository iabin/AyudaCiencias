/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.*;


/**
 *
 * @author jonathan
 */
@ManagedBean
@SessionScoped
public class BuscarContenido  {
    
    
    private String buscar;
    private List<Pregunta> resultado = new ArrayList<Pregunta>();

    public List<Pregunta> getResultado() {
        return resultado;
    }

    public void setResultado(List<Pregunta> resultado) {
        this.resultado = resultado;
    }

    
    public String getBuscar() {
        return buscar;
    }

    
    public void setBuscar(String aBuscar){ 
        this.buscar = aBuscar;
        
    }
    
    public void busca(){
        
        this.resultado = new PreguntaDAO().buscaPorTitulo(buscar);
        if(this.resultado.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("idspecified", new FacesMessage("Sin resultados", "no hay resultados para la busqueda"));

        }
    }


   
}
