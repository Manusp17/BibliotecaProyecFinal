
package DAO;

import Modelo.Tesis;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TesisDAO {
    
     public boolean insertar(Tesis tesis) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(tesis);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Tesis tesis) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(tesis);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrar(Tesis tesis) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(tesis);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Tesis buscarPorId(int id) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tesis.class, id);
        }
    }

    public List<Tesis> buscarTodo() {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tesis").list();
        }
    }
}
