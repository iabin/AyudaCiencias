package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *Clase que hace consultas con la tabla de respuesta
 * @author Iabin
 */
public class RespuestaDAO {
    private SessionFactory sessionFactory;
    /**
     * Constructor de la clase.
     * inicializa la variable sessionFactory para que podamos abrir una session
    */
    public RespuestaDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * Devuelve una lista con TODAS las preguntas
     * @return lista con todas las preguntas
     */
    public List<Respuesta> respuestas() {
        List<Respuesta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Respuesta";
            Query query = session.createQuery(hql);
            result = (List<Respuesta>)query.list();
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
     * Busca una respuesta con un id dado
     * @param id a buscar 
     * @return respuesta con ese ID
     */
    public Respuesta buscaRespuesta(int id) {
        List<Respuesta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Respuesta as u where u.idrespuesta = "+id;
            Query query = session.createQuery(hql);
            result = (List<Respuesta>)query.list();
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
        return result.get(0);
    }
    
    /**
     * Guarda la respuesta dada en la base de datos
     * @param u Respuesta a guardar
     * @return true si la consulta fue exitosa, false en otro caso
     */
    public boolean nuevaRespuesta(Respuesta u){
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
    
    public boolean eliminaRespuesta(Respuesta u){
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
     * Actualiza una respuesta 
     * @param u respuesta a actualizar
     * @return  true si fue exitosa, false en otro caso
     */
    public boolean actualizaRespuesta(Respuesta u){
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
