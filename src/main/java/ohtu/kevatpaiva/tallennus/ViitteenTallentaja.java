package ohtu.kevatpaiva.tallennus;

import java.util.List;
import java.util.Properties;
import ohtu.kevatpaiva.Viite;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ViitteenTallentaja {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;
    private static Session session = null;
    Transaction tx = null;

    public ViitteenTallentaja() {
        /**
         * Vain yksi sessio / prosessi!
         */
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
    }

    /**
     * Tallenna viite tietokantaan
     *
     * Huom! Koska transaktio-olio <code>tx</code> on luokan attribuuttina, 
     * transaktio pitää lopettaa explisiittisesti metodin sisällä.
     *
     * @param viite
     * @throws Exception
     */
    public void tallenna(Object viite) throws Exception {
        
        try {
            
            tx = session.beginTransaction();
            session.save(viite);
            session.flush();
            tx.commit();

        } catch (Exception ex) {
            
            /**
             * Suorita <code>rollback()</code> kaikissa poikkeustapauksissa.
             */
            //ex.printStackTrace();
            tx.rollback();
            System.out.println("ViitteenTallentaja.tallenna: " + ex.getMessage());
            throw ex;
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public List<Viite> listaa() {

        List<Viite> viiteLista;
        
        try {
            
            tx = session.beginTransaction();
            viiteLista = session.createQuery("from Viite").list();
            session.flush();
            tx.commit();
            
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        
        return viiteLista;
    }

    public boolean onkoViite(String id) {

        List<Viite> viiteLista;
        
        try {

            tx = session.beginTransaction();
            Query query = session.createQuery("from Viite WHERE id = :viite_id");
            query.setParameter("viite_id", id);
            viiteLista = query.list();
            session.flush();
            tx.commit();
            
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        
        for (Viite viite : viiteLista) {

            System.out.println(viite.getId());
            
            if (viite.getId().equalsIgnoreCase(id)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void poista(String id) {
        
        if (onkoViite(id)) {
            
            try {

                tx = session.beginTransaction();
                Query query = session.createQuery("delete from Viite where id = :viiteId ");
                query.setString("viiteId", id);
                query.executeUpdate();
                session.flush();
                tx.commit();
                
            } finally {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }
        }
    }

    public Viite haeIdlla(String id) {
        
        Viite viite;
        
        try {
            
            tx = session.beginTransaction();
            viite = (Viite) session.get(Viite.class, id);
            session.flush();
            tx.commit();
            
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        
        return viite;
    }

    public Session getSession() {
        return session;
    }
}
