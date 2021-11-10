package chap07.composite_key;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CompositeKeyAapp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Parent parent = new Parent();
        parent.setId1("parent1");
        parent.setId2("parent2");
        parent.setName("마들렌느 시장");
        em.persist(parent);

        Child child = new Child();
        child.setId("child1");
        child.setParent(parent);
        em.persist(child);
        tx.commit();

        tx.begin();
        ParentId parentId = new ParentId("parent1", "parent2");
        Parent parent1 = em.find(Parent.class, parentId);
        System.out.println(parent1.toString());


    }
}
