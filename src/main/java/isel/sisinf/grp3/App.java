package isel.sisinf.grp3;

import isel.sisinf.ap6.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;


/**
 * Hello world!
 */
public class App {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("HELLO WORD");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("t42dg3");
        EntityManager em = emf.createEntityManager();
        try {
            //CREATE
            System.out.println("--# CREATE Country");
            em.getTransaction().begin();

            Country cn = new Country();
            cn.setName("France");
            em.persist(cn);
            em.getTransaction().commit();

            System.out.println("ID Generated:" + cn.getCountryId());

            //READ
            System.out.println("\n--# READ Country");

            String sql = "SELECT c FROM Country c";
            Query query = em.createQuery(sql);
            List<Country> country = query.getResultList();

            for (Country c : country) {
                System.out.printf("%d ", c.getCountryId());
                System.out.printf("%s \n", c.getName());
            }

            //DELETE
            em.getTransaction().begin();
            em.remove(em.find(Country.class, cn.getCountryId()));
            em.flush(); //Send changes to database
            em.getTransaction().commit();
            em.clear();
            System.out.println("\n--# Removed!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}

