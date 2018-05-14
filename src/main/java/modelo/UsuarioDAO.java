/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Alan
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
