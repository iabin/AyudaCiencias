package controlador;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.*;


@ManagedBean
@RequestScoped
/**
 * Clase que modela el caso de uso de agregar pregunta
 * @author alan
 */
public class AgregarPregunta {
    private String contenido;
    
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    /**
     * Guarda una pregunta con el id del usuario dado
     * @param id id del usuario dado
     * @return string con redireccion
     */
    public String agregarPregunta(String id){
        Pregunta pregunta=new Pregunta();
        UsuarioDAO ud=new UsuarioDAO();
        pregunta.setContenido(contenido);
        pregunta.setUsuario(ud.buscaUsuario(Integer.parseInt(id)));
        PreguntaDAO pd=new PreguntaDAO();
        Boolean r = pd.nuevaPregunta(pregunta);
        return "index.xhtml?faces-redirect=true";
    }
}
