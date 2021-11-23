package chap09.value_type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ValueTypeApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        embeddableTest(em);
    }

    static void embeddableTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Employer employer = new Employer();
        employer.setName("빅또르위고");

        PhoneServiceProvider provider = new PhoneServiceProvider();
        provider.setName("SKT");

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setAreaCode("+82");
        phoneNumber.setLocalNumber("02");
        phoneNumber.setProvider(provider);

        employer.setPhoneNumber(phoneNumber);

        em.persist(employer);
        tx.commit();

    }
}
