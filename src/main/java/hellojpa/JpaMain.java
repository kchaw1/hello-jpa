package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Member2 member2 = new Member2();

            Address address = new Address("Seoul", "nowon", "00000");

            member2.setName("KIM");
            member2.setAddress(address);
            member2.getAddresses().add(new AddressEntity(address.getCity(), address.getStreet(), address.getZipcode()));
            member2.getAddresses().add(new AddressEntity("Busan", "Haeundae", "99999"));

            member2.getFavoriteFood().add("치킨");
            member2.getFavoriteFood().add("피자");
            member2.getFavoriteFood().add("햄버거");

            em.persist(member2);

            em.flush();
            em.clear();

            Member2 findMember = em.find(Member2.class, member2.getId());

            List<AddressEntity> addresses = findMember.getAddresses();

            for (AddressEntity addressEntity : addresses) {
                System.out.println(addressEntity.getAddress().getClass());
                System.out.println(addressEntity.getAddress().getCity());
            }

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
