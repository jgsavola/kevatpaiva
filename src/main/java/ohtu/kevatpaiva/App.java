package ohtu.kevatpaiva;

import java.util.List;
import java.util.Properties;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
 
/**
 * Hello world!
 *
 */
public class App {
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
       
    private static SessionFactory configureSessionFactory() throws HibernateException { 
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
         
        Properties properties = configuration.getProperties();
         
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
         
        return sessionFactory; 
    }
     
    public static void main(String[] args) {
        // Configure the session factory
        configureSessionFactory();
         
        Session session = null;
        Transaction tx=null;
         
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
             
            // Creating Article entity that will be save to the sqlite database
            Article article1 = new Article(1, "Whittington, Keith J.", "Infusing active learning into introductory programming courses", 2004);
            Article article2 = new Article(2, "Allan Collins and John Seely Brown and Ann Holum", "Cognitive apprenticeship: making thinking visible", 1991);
             
            // Saving to the database
            session.save(article1);
            session.save(article2);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            // Fetching saved data
            List<Article> articleList = session.createQuery("from Article").list();
             
            for (Article article : articleList) {
                System.out.println("Id: " + article.getId() + " | Name:"  + article.getAuthor() + " | Email:" + article.getTitle());
            }
             
        } catch (Exception ex) {
            ex.printStackTrace();
             
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }
}
