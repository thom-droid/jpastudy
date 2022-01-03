package chap07.join_strategy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JoinedStrategyApp {

    public static void main(String[] args) {
        System.out.println("test");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        populateItem(em);

    }

    public static void populateItem(EntityManager em){

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Album okNotOK = new Album();

        okNotOK.setArtist("radiohead");
        okNotOK.setName("4wd");
        okNotOK.setPrice(13000);

        Album amnesiac = Album.builder()
                .name("amnesiac")
                .artist("radiohead")
                .price(29000)
                .build();

        Album ambient1 = Album.builder()
                .name("ambientwork1")
                .artist("brian eno")
                .price(15000)
                .build();

        Album refugee = Album.builder()
                .name("refugee")
                .artist("devendra banhart")
                .price(20000)
                .build();

        Album ambient2 = Album.builder()
                .name("ambient2")
                .artist("brian eno")
                .price(17000)
                .build();

        Album muzz = Album.builder()
                .name("muzz")
                .artist("muzz")
                .price(22000)
                .build();

        em.persist(okNotOK);
        em.persist(ambient1);
        em.persist(amnesiac);
        em.persist(refugee);
        em.persist(ambient2);
        em.persist(muzz);

        tx.commit();
    }
}
