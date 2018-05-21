package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *Clase que hace consultas en la tabla de Pregunta
 * @author Iabin
 */
public class PreguntaDAO {
    private SessionFactory sessionFactory;
    /**
     * Constructor de la clase.
     * inicializa la variable sessionFactory para que podamos abrir una session
    */
    public PreguntaDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
  
      /**Constructor que devuelve una lista con todas las preguntas*/
    public List<Pregunta> buscaPorTitulo(String aBuscar) {
        List<Pregunta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Pregunta  as p where lower(p.contenido) like lower('%"+ aBuscar +"%')";
            
            Query query = session.createQuery(hql);
            result = (List<Pregunta>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    /**Constructor que devuelve una lista con todas las preguntas*/
    public List<Pregunta> preguntas() {
        List<Pregunta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Pregunta";
            Query query = session.createQuery(hql);
            result = (List<Pregunta>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
    /**
     * Constructor que busca una pregunta por su id
     * @param id id de la pregunta
     * @return Pregunta que coincide el id
     */
    public Pregunta buscaPregunta(int id) {
        Pregunta result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Pregunta as u where u.idpregunta = "+id;
            Query query = session.createQuery(hql);
            result = (Pregunta)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //result.get(0).getRespuestas().size();
            //cerramos la session
            session.close();
        }
        return result;
    }
    
    /**
     * Metodo que crea una nueva pregunta en la base de datos
     * @param u pregunta a guardar
     * @return true si la guardo de manera correcta, false en otro caso
     */
    public boolean nuevaPregunta(Pregunta u){
         // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            session.persist(u);
            
            tx.commit();
        }
        catch (Exception er) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           er.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return true;
    
    }
    /**
     * Elimina una pregunta dada
     * @param u la pregnta a eliminar
     * @return true si se elimino con exito 
     */
    public boolean eliminaPregunta(Pregunta u){
         // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            session.delete(u);
            
            tx.commit();
        }
        catch (Exception er) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           er.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return true;
    
    }
    /**
     * Actualiza la pregunta dada
     * @param u pregunta a actualizar
     * @return true si la actualizacion fue exitosa
     */
    public boolean actualizaPregunta(Pregunta u){
         // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            session.update(u);
            
            tx.commit();
        }
        catch (Exception er) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           er.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return true;
    
    }
}
