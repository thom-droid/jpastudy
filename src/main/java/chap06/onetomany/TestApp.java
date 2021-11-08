package chap06.onetomany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestApp {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member m1 = new Member("m1");
        Member m2 = new Member("m2");
        Team t1 = new Team("t1");

        t1.getMembers().add(m1);
        t1.getMembers().add(m2);

        em.persist(t1);
        em.persist(m1);
        em.persist(m2);

        tx.commit();

    }


}
