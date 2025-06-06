
package DAO;

import Modelo.Usuario;
import Persistencia.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {
    
    public boolean insertar(Usuario usuario) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(usuario);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuario usuario) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(usuario);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean borrar(Usuario usuario) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(usuario);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error al borrar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarPorId(int id) {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar usuario por ID: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarTodo() {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario").list();
        } catch (Exception e) {
            System.err.println("Error al buscar todos los usuarios: " + e.getMessage());
            return null;
        }
    }
}
