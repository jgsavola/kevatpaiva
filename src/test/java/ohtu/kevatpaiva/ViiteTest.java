/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonne
 */
public class ViiteTest {
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

    public ViiteTest() {
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
            List<Viite> viiteList = session.createQuery("from Viite").list();
            for (Viite v : viiteList) {
                session.delete(v);
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
     * Testaa viitteiden luomista.
     */
    @Test
    public void testaaViitteidenLuomista() {
        System.out.println("Luo viitteitä.");

        Transaction tx=null;

        try {
            tx = session.beginTransaction();

            ViiteTyyppi vt = ViiteTyyppiTehdas.luoViiteTyyppi("article");
            KenttaTehdas kt = new KenttaTehdas(vt);

            Viite viite1 = new Viite("W04", vt,
                    kt.luoKentta("author", "Whittington, Keith J."),
                    kt.luoKentta("title", "Infusing active learning into introductory programming courses"),
                    kt.luoKentta("year", "2004"));

            Viite viite2 = new Viite("CBH91", vt,
                    kt.luoKentta("author", "Allan Collins and John Seely Brown and Ann Holum"),
                    kt.luoKentta("title", "Cognitive apprenticeship: making thinking visible"),
                    kt.luoKentta("year", "1991"),
                    kt.luoKentta("note", "Joku ääkkösiä sisältävä \"note\"."));

            // Saving to the database
            session.save(viite1);
            session.save(viite2);

            // Committing the change in the database.
            session.flush();
            tx.commit();

            // Fetching saved data
            List<Viite> viiteList = session.createQuery("from Viite").list();

            assertEquals("Viitteitä on talletettu 2", viiteList.size(), 2);

            for (Viite viite : viiteList) {
                System.out.println("Viite Id: " + viite.getId()); // + " | Name:"  + viite.getAuthor() + " | Email:" + viite.getTitle());
                System.out.println(viite.toBibTeX());
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
        }
    }
}