package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Address address = new Address();
            address.setCity("Seoul");
            address.setStreet("Nowon");
            address.setZipcode("00000");

            Address address2 = new Address();
            address2.setCity("Seoul");
            address2.setStreet("Seongdong");
            address2.setZipcode("11111");

            Period period = new Period();
            period.setStartDate(LocalDateTime.now());
            period.setEndDate(LocalDateTime.now());

            Member2 member2 = new Member2();
            member2.setName("KIM");
            member2.setPeriod(period);
            member2.setAddress(address);
            member2.setWorkAddress(address2);

            em.persist(member2);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
