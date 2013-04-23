package ohtu.kevatpaiva;

import java.util.List;
import java.util.Properties;
import ohtu.kevatpaiva.tallennus.ArtikkelinTallentaja;

import org.junit.Test;
import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 * Testaa Article-luokan toimintaa.
 *
 * @author jgsavola
 */
public class ArticleTest {
    
    ArtikkelinTallentaja tallentaja;
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
    
    private static Session session = null;
       
    private static SessionFactory configureSessionFactory() throws HibernateException { 
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
         
        Properties properties = configuration.getProperties();
        
        /**
         * Käytä testaukseen erillistä tietokantatiedostoa, koska
         * testit muokkaavat tietokantaa. Näin käyttäjän tietokantaa
         * ei tarvitse muuttaa.
         */
        properties.setProperty("hibernate.connection.url", "jdbc:sqlite:kevatpaiva.test.db");
        
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
         
        return sessionFactory; 
    }
         
    public ArticleTest() {
    }
    
    /**
     * Poista kaikki artikkelit ennen testejä.
     *
     * @throws Exception 
     */
    @BeforeClass
    public static void setUp() throws Exception {
        
        System.out.println("setUp");

        configureSessionFactory();
        
        session = null;
        Transaction tx=null;
         
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        
        /**
         * FIXME: Pitää miettiä uudestaan tämä rakenne.
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
    public void testaaArtikkelinLuomista() {
        System.out.println("Luo artikkeleita.");

        Transaction tx=null;
         
        try {
            tx = session.beginTransaction();
             
            // Creating Article entity that will be save to the sqlite database
            Article article1 = new Article("article","W04", "Whittington, Keith J.", "Infusing active learning into introductory programming courses", "2004", "ACM", null,null,null);
            Article article2 = new Article("article","CBH91", "Allan Collins and John Seely Brown and Ann Holum", "Cognitive apprenticeship: making thinking visible", "1991", "ACM", null, null, null);
             
            // Saving to the database
            session.save(article1);
            session.save(article2);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            // Fetching saved data
            List<Article> articleList = session.createQuery("from Article").list();
            
            assertEquals("Artikkeleita on talletettu 2", articleList.size(), 2);
            
            for (Article article : articleList)
                System.out.println("Id: " + article.getId() + " | Name:"  + article.getAuthor() + " | Email:" + article.getTitle());
        } catch (Exception ex) {
            ex.printStackTrace();
             
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
        }
    }

    /**
     * ID-kenttä ei saa olla null, koska se on avainkenttä.
     */
    @Test
    public void testaaNullIDKentta() {
        System.out.println("Yritä luoda artikkeli ID:llä null.");

        Transaction tx=null;
         
        try {
            tx = session.beginTransaction();
             
            // Creating Article entity that will be save to the sqlite database
            Article article1 = new Article("article", null, "NULL ID author", "NULL ID title", "2004", "ACM", null,null,null);
             
            // Saving to the database
            session.save(article1);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            fail("Pitäisi tulla poikkeus ID:stä null");
        } catch (org.hibernate.id.IdentifierGenerationException ex) {
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        }  catch (Exception ex) {
            ex.printStackTrace();
             
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
            fail("Odottamaton poikkeus " + ex);         
        }
        finally{
        }
    }

    /**
     * Testaa artikkeleiden hakemista. Huom! tämä testi pitää ajaa 
     * testaaArtikkelinLuomista-testin jälkeen.
     */
    @Test
    public void testaaArtikkelinHakemista() {
        System.out.println("Hae artikkeleita.");

        Transaction tx=null;
         
        try {
            tx = session.beginTransaction();
            
            List<Article> articleList = session.createQuery("from Article where author like '%Holum%'").list();
            System.out.println("Artikkeleita löytyi: "+articleList.size());
            assertEquals("Artikkeleita on löytyi 1", 1, articleList.size());
            //assertEquals("Oikea artikkeli löytyi", "Allan Collins and John Seely Brown and Ann Holum", articleList.get(0).getAuthor());
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

    /**
     * Testaa BibTeX-esityksen tuottamista.
     */
    @Test
    public void testaaArtikkelinBibTeXEsitysta() {
        System.out.println("Luo BibTeX-esitys.");

        Article article1 = new Article("article","W04", "Whittington, Keith J.", "Infusing active learning into introductory programming courses", "2004", "ACM", null,null,null);

        String bibtex_ok = "@article{W04,\n"
            + "    author = {Whittington, Keith J.},\n"
            + "    title = {{I}nfusing active learning into introductory programming courses},\n"
//            + "    volume = {null},\n"
//            + "    number = {null},\n"
            + "    journal = {ACM},\n"
            + "    year = {2004},\n"
            + "}\n";

        String bibtex = article1.toBibTeX();
        assertEquals("Artikkelin BibTeX-esitys on oikein.", bibtex_ok, bibtex);
    }
    
    
}
