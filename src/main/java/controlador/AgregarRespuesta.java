package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.*;


@ManagedBean
@RequestScoped
/**
 *
 * @author alan
 */
public class AgregarRespuesta {
    private String contenido;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public String agregarRespuesta(String id,String idp){
        Respuesta respuesta=new Respuesta();
        RespuestaDAO rd=new RespuestaDAO();
        PreguntaDAO pd=new PreguntaDAO();
        UsuarioDAO ud=new UsuarioDAO();
        respuesta.setContenido(contenido);
        respuesta.setUsuario(ud.buscaUsuario(Integer.parseInt(id)));
        respuesta.setPregunta(pd.buscaPregunta(Integer.parseInt(idp)));
        
        Boolean r = rd.nuevaRespuesta(respuesta);
        
        return "index.xhtml?faces-redirect=true";
    }
}
