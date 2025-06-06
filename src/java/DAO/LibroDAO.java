
package DAO;

import Modelo.Libro;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LibroDAO {
    
    public boolean insertar(Libro libro) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(libro);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Libro libro) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(libro);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean borrar(Libro libro) {
        Transaction tx = null;
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(libro);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Libro buscarPorId(int id) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.get(Libro.class, id);
        }
    }

    public List<Libro> buscarTodo() {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro").list();
        }
    }
}
