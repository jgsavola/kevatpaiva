/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kevatpaiva.tallennus;

import java.util.List;
import java.util.Properties;
import ohtu.kevatpaiva.Article;
import org.hibernate.HibernateException;
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
    
    public void tallennaArtikkeli(Article artikkeli) {
        try {
            tx = session.beginTransaction();
             
            // Saving to the database
            session.save(artikkeli);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
        }
        finally {
            
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
}
