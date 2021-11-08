package chap06.onetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        LockerUser user = new LockerUser("user1");
        Locker locker1 = new Locker("locker1");

        user.setLocker(locker1);

        em.persist(user);
        em.persist(locker1);

        tx.commit();
    }
}
