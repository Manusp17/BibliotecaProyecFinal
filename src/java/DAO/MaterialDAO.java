
package DAO;

import Modelo.Material;
import Persistencia.NewHibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;


public class MaterialDAO {
    
   public List<Material> buscarTodo(){
    
       Session session = null;
       Transaction tx = null;
       List<Material> lista = null;
       try {
           session = NewHibernateUtil.getSessionFactory().openSession();
           tx = session.beginTransaction();
           
           Query query = session.createQuery("from Planilla");
           lista = query.list();
           
           tx.commit();;           
       } catch (Exception e) {
           if(tx != null) tx.rollback();
           e.printStackTrace();
       }finally{
           if(session != null) session.close();
       }
       
       return lista;
   }
    
   public boolean borrar (Material material){
       Session session = null;
       Transaction tx = null;
       boolean flag = false;
       
       try {
           session = NewHibernateUtil.getSessionFactory().openSession();
           tx = session.beginTransaction();
           
           Material m = (Material) session.merge(material);
           session.delete(m);
           
           tx.commit();
           flag = true;
       } catch (Exception e) {
           if(tx != null) tx.rollback();
           e.printStackTrace();
       }finally{
           if(session != null) session.close();
       }
       
       return flag;
   }
   
    public boolean insertar(Material material) {
        Session session = null;
        Transaction tx = null;
        boolean flag = false;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(material); // Inserta si es nuevo, actualiza si tiene ID
            tx.commit();

            flag = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace(); // Usa logger si puedes
        } finally {
            if (session != null) session.close();
        }

        return flag;
    }
    
    public boolean actualizar(Material material) {
        Session session = null;
        Transaction tx = null;
        boolean flag = false;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.update(material); // actualizar entidad ya existente
            tx.commit();

            flag = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return flag;
    }
}
