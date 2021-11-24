package chap07.mapped_super_class;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappedSuperClassApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        elementCollectionTest(em);

    }

    private static void mappedSuperClassTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setName("꼬제트");
        member.setEmail("꼬제트@gmail.com");
        em.persist(member);

        Seller seller = new Seller();
        seller.setName("마리우스");
        seller.setShopName("장발장의 바게뜨");
        em.persist(seller);

        tx.commit();

    }
    private static void elementCollectionTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member m1 = new Member();
        m1.setName("자와자와");
        m1.setEmail("jawajawa@naver.com");
        m1.getNickNames().add("세리자와");
        m1.getNickNames().add("카이지");
        m1.getNickNames().add("오마에와");
        em.persist(m1);
        tx.commit();
    }
}
