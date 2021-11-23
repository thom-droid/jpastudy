package chap08.proxy;

import chap07.mapped_super_class.Member;
import chap07.mapped_super_class.Team;
import org.h2.engine.SysProperties;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class ProxyApp {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
//        collectionWrapperTest(em);
    }

    public static void loadingTest(EntityManager em){

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member();
        member.setName("꼬제트");
        member.setEmail("꼬제트@gmail.com");
        em.persist(member);
        tx.commit();
        em.clear();

        Member findMember = em.getReference(Member.class, 1L); // return proxy
        boolean isLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(findMember);
        Hibernate.initialize(findMember);
        boolean isForciblyLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(findMember);

        boolean isLoaded2 = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
        boolean isLoaded3 = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(findMember);
        System.out.println(isLoaded);
        System.out.println(isForciblyLoaded);
        System.out.println(isLoaded2);
        System.out.println(isLoaded3);

        System.out.println(member.getClass().getName());
        System.out.println(findMember.getClass().getName());
    }

    public static void collectionWrapperTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member();
        member.setName("꼬제트");
        member.setEmail("꼬제트@gmail.com");

        Team team = new Team();
        team.setName("blue");
        member.addTeam(team);;
        em.persist(team);
        em.persist(member);


        tx.commit();

        em.clear();

        Team t2 = em.find(Team.class, 1L);
        Member m = em.find(Member.class, 1L);
        List<Member> members = t2.getMembers();
        System.out.println(members.getClass().getName());

    }
}
