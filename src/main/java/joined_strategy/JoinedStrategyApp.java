package joined_strategy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JoinedStrategyApp {

    public static void main(String[] args) {
        System.out.println("test");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Album okNotOK = new Album();

        okNotOK.setArtist("radiohead");
        okNotOK.setName("4wd");
        okNotOK.setPrice(13000);
        em.persist(okNotOK);

        tx.commit();

    }
}
