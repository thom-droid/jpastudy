package chap08.proxy;

import chap07.mapped_super_class.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyApp {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member();
        member.setName("꼬제트");
        member.setEmail("꼬제트@gmail.com");
        em.persist(member);

        Member findMember = em.getReference(Member.class, 1L); // return proxy
        System.out.println(findMember.getName()); // proxy initialization
        tx.commit();


    }
}
