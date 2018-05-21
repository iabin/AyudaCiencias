package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *Clase que hace consultas a la tabla usuario
 * @author Iabin
 */
public class UsuarioDAO {
    
    private SessionFactory sessionFactory;
    /**
     * Constructor de la clase.
     * inicializa la variable sessionFactory para que podamos abrir una session
    */
    public UsuarioDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * Devuelve una lista con todos los usuarios
     * @return una lista con todos los usuarios
     */
    public List<Usuario> usuarios() {
        List<Usuario> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Usuario";
            Query query = session.createQuery(hql);
            result = (List<Usuario>)query.list();
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
     * Busca un usario segun id
     * @param id id a buscar
     * @return el usuario con ese id
     */
    public Usuario buscaUsuario(int id) {
        List<Usuario> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Usuario as u where u.idusuario = "+id;
            Query query = session.createQuery(hql);
            result = (List<Usuario>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            result.get(0).getPreguntas().size();
            result.get(0).getRespuestas().size();
            //cerramos la session
            session.close();
        }
        return result.get(0);
    }
    
    /**
     * Guarda en la base a un usuario
     * @param u usuario a guardar
     * @return true si tuvo exito false en otro caso
     */
    public boolean nuevoUsuario(Usuario u){
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
     * Elimina un usuario dado 
     * @param u usuario a eliminar
     * @return true si fue exitoso, false en otro caso
     */
    public boolean eliminaUsuario(Usuario u){
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
    
    public boolean actualizaUsuario(Usuario u){
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
