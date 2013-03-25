package ohtu.kevatpaiva;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author jgsavola
 */
public class ArticleTest {
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
    
    private static Session session = null;
       
    private static SessionFactory configureSessionFactory() throws HibernateException { 
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
         
        Properties properties = configuration.getProperties();
         
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
         
        return sessionFactory; 
    }
         
    public ArticleTest() {
        System.out.println("Constructor");
    }
    
    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("setUp");

        configureSessionFactory();
        
        session = null;
        Transaction tx=null;
         
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        
        /**
         * Pitää miettiä uudestaan tämä rakenne.
         */
        try {
            /**
             * Tyhjennä taulu testejä varten.
             */
            List<Article> articleList = session.createQuery("from Article").list();
            for (Article a : articleList) {
                session.delete(a);
            }
            
            tx.commit();
            tx = null;
        } finally {
            if (tx != null)
                tx.rollback();
        }

    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        session.close();
    }

    /**
     * Testaa artikkeleiden luomista.
     */
    @Test
    public void testaArtikkelinLuomista() {
        System.out.println("Luo artikkeleita.");

        Transaction tx=null;
         
        try {
            tx = session.beginTransaction();
             
            // Creating Article entity that will be save to the sqlite database
            Article article1 = new Article("W04", "Whittington, Keith J.", "Infusing active learning into introductory programming courses", 2004);
            Article article2 = new Article("CBH91", "Allan Collins and John Seely Brown and Ann Holum", "Cognitive apprenticeship: making thinking visible", 1991);
             
            // Saving to the database
            session.save(article1);
            session.save(article2);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            // Fetching saved data
            List<Article> articleList = session.createQuery("from Article").list();
            
            assertEquals("Artikkeleita on talletettu 2", articleList.size(), 2);
            
            for (Article article : articleList) {
                System.out.println("Id: " + article.getId() + " | Name:"  + article.getAuthor() + " | Email:" + article.getTitle());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
             
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
        }
    }

    /**
     * Testaa artikkeleiden hakemista.
     */
    @Test
    public void testaArtikkelinHakemista() {
        System.out.println("Hae artikkeleita.");

        Transaction tx=null;
         
        try {
            tx = session.beginTransaction();
             
            List<Article> articleList = session.createQuery("from Article where author like '%Holum%'").list();

            assertEquals("Artikkeleita on löytyi 1", 1, articleList.size());
            assertEquals("Oikea artikkeli löytyi", "Allan Collins and John Seely Brown and Ann Holum", articleList.get(0).getAuthor());
            tx.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
             
            fail("Poikkeus artikkeleiden hakemisesssa.");
            
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
        }
    }
}
