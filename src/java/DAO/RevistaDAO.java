
package DAO;

import Modelo.Revista;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class RevistaDAO {
    
     public boolean insertar(Revista revista) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(revista);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Revista revista) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(revista);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrar(Revista revista) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(revista);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Revista buscarPorId(int id) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.get(Revista.class, id);
        }
    }

    public List<Revista> buscarTodo() {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Revista").list();
        }
    }
}
