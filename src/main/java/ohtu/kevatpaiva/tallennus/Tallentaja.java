/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kevatpaiva.tallennus;

import java.util.List;
import java.util.Properties;
import ohtu.kevatpaiva.Article;
import ohtu.kevatpaiva.Reference;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author hwikgren
 */
public class Tallentaja {
    
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
    
    private static Session session = null;
    Transaction tx=null;

    public Tallentaja() {
        
        if (session == null) {
            
            configureSessionFactory();
            session = sessionFactory.openSession();
        }

    }
    
    
    private static void configureSessionFactory() throws HibernateException { 
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
         
        Properties properties = configuration.getProperties();
         
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
         
        //return sessionFactory; 
    }
    
    /**
     * Tallenna artikkeli tietokantaan.
     *
     * Huom! Koska transaktio-olio <code>tx</code> on luokan attribuuttina, 
     * transaktio pitää lopettaa explisiittisesti metodin sisällä.
     *
     * @param artikkeli
     * @throws Exception 
     */
    public void tallennaReferencet(Object object) throws Exception {
        try {
            tx = session.beginTransaction();
             
            // Saving to the database
            session.save(object);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            /**
             * Suorita <code>rollback()</code> kaikissa 
             * poikkeustapauksissa.
             */
            ex.printStackTrace();
            tx.rollback();
            throw ex;
        }
    }
    
    public List<Reference> listaaReferencet() {
        
        List<Reference> articleList;
        try {
            tx = session.beginTransaction();
             
            articleList = session.createQuery("from Reference").list();
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        return articleList;
    }
    
    public boolean onkoReference(String id) {
        
        List<Reference> articleList;
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("from Reference WHERE id = :id");
            query.setParameter("id", id);
            articleList = query.list();
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        for (Reference article : articleList) {
                System.out.println(article.getId());
                if (article.getId().equalsIgnoreCase(id)) {
                    return true;
                }
                else return false;
            }
        return false;
    }
    
    public void poistaViite(String id) {
        if (onkoReference(id)) {
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("delete from Reference where id = :viiteId ");
            query.setString("viiteId", id);
            query.executeUpdate();
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        }
    }
    
    public Reference haeIdlla(String id) {
        Reference article;
        try {
            tx = session.beginTransaction();
             
            article = (Reference) session.get(Reference.class, id);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        return article;
    } 
}
