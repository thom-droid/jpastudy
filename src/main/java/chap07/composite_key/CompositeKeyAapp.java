package chap07.composite_key;

import chap07.composite_key.embedded_id.ChildId;
import chap07.composite_key.embedded_id.GrandChildId;

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
        parent.setName("mother");
        parent.setId("parent1");
        em.persist(parent);

        Child child = new Child();

        ChildId childId = new ChildId("parent1", "child1");
        child.setId(childId);
        child.setParent(parent);
        child.setName("boy");
        em.persist(child);

        GrandChild grandChild = new GrandChild();
        GrandChildId grandChildId = new GrandChildId(childId, "grandchild1");
        grandChild.setId(grandChildId);
        grandChild.setChild(child);
        grandChild.setName("yummy");
        em.persist(grandChild);

        tx.commit();


    }
}
