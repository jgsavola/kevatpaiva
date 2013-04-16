/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kevatpaiva.tallennus;

import java.util.List;
import java.util.Properties;
import ohtu.kevatpaiva.Article;
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
public class ArtikkelinTallentaja {
    
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
    
    private static Session session = null;
    Transaction tx=null;

    public ArtikkelinTallentaja() {
        configureSessionFactory();
        
         
        session = sessionFactory.openSession();
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
    public void tallennaArtikkeli(Article artikkeli) throws Exception {
        try {
            tx = session.beginTransaction();
             
            // Saving to the database
            session.save(artikkeli);
             
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
    
    public List<Article> listaaArtikelit() {
        
        List<Article> articleList;
        try {
            tx = session.beginTransaction();
             
            articleList = session.createQuery("from Article").list();
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        return articleList;
    }
    
    public boolean onkoArtikkeli(String id) {
        
        List<Article> articleList;
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("from Article WHERE id = :article_id");
            query.setParameter("article_id", id);
            articleList = query.list();
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
        }
        for (Article article : articleList) {
                System.out.println(article.getId());
                if (article.getId().equalsIgnoreCase(id)) {
                    return true;
                }
                else return false;
            }
        return false;
    }
    
    public void poistaViite(String id) {
        if (onkoArtikkeli(id)) {
        try {
            tx = session.beginTransaction();
             
            Query query = session.createQuery("delete from Article where id = :viiteId ");
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
}
