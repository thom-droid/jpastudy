package chap09.value_type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypeApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
//        embeddableTest(em);
        valueTypeCollectionTest(em);
        valueTypeCollectionSearchTest(em);
        valueTypeCollectionUpdate(em);
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

    private static void valueTypeCollectionTest(EntityManager em){

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Employer employer = new Employer();

        employer.setHomeAddress(new Address("서울","영등포","신길동"));
        employer.getFavoriteFood().add("차돌짬뽕");
        employer.getFavoriteFood().add("간짜장");
        employer.getFavoriteFood().add("새우볶음밥");

        employer.getAddressHistory().add(new Address("서울","동대문","무슨무슨동"));
        employer.getAddressHistory().add(new Address("남양주","무슨무슨구","화도읍"));

        em.persist(employer);
        tx.commit();

    }

    private static void valueTypeCollectionSearchTest(EntityManager em){

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Employer employer = em.find(Employer.class, 1L);
        boolean isLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(employer);
        System.out.println("employer loaded?" + isLoaded);

        Address homeAddress = employer.getHomeAddress();
        System.out.println("home addr : "+ homeAddress);

        Set<String> favoriteFood = employer.getFavoriteFood();
        for(String s: favoriteFood){
            System.out.println("favorite food : " + s);
        }

        List<Address> addrHistory = employer.getAddressHistory();
        System.out.println(addrHistory.get(0));

    }

    private static void valueTypeCollectionUpdate(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        Employer employer = em.find(Employer.class, 1L);
        employer.setHomeAddress(new Address("뉴도시","뉴동","뉴읍"));

        Set<String> favoriteFood = employer.getFavoriteFood();
        favoriteFood.remove("새우볶음밥");
        favoriteFood.add("고추잡채");

        List<Address> addressList = employer.getAddressHistory();
        addressList.remove(new Address("서울","동대문","무슨무슨동"));
        addressList.add(new Address("새도시","새동","새읍"));
        tx.commit();

    }
}
