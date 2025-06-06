
package DAO;

import Modelo.CD;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CDDAO {
    
    public boolean insertar(CD cd) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(CD cd) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrar(CD cd) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public CD buscarPorId(int id) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.get(CD.class, id);
        }
    }

    public List<CD> buscarTodo() {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM CD").list();
        }
    }
}
