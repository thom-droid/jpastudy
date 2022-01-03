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
//        saveWithoutCascade(em);
        saveWithCascade(em);
//        deleteWithoutCascade(em);
//        deleteWithCascade(em);
//        removeOrphanObject(em);
//        valueTypeCollectionUpdateTest(em);
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

    public static void saveWithoutCascade(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("A");
        em.persist(restaurant1);

        Dish dish1 = new Dish();
        dish1.setName("a");
        dish1.setRestaurant(restaurant1);
        restaurant1.getDishes().add(dish1);
        em.persist(dish1);

        Dish dish2 = new Dish();
        dish2.setName("b");
        dish2.setRestaurant(restaurant1);
        restaurant1.getDishes().add(dish2);
        em.persist(dish2);

        tx.commit();
    }

    public static void saveWithCascade(EntityManager em){
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("A");

        Dish dish1 = new Dish();
        dish1.setName("a");
        dish1.setRestaurant(restaurant1);
        restaurant1.getDishes().add(dish1);

        Dish dish2 = new Dish();
        dish2.setName("b");
        dish2.setRestaurant(restaurant1);
        restaurant1.getDishes().add(dish2);

        Dish dish = new Dish();
        dish.setName("c");
        dish.setRestaurant(restaurant1);
        restaurant1.getDishes().add(dish);

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("깍두기");
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredient("진라면");
        dish.getIngredientSet().add(ingredient);
        dish.getIngredientSet().add(ingredient1);

        em.persist(restaurant1);

        tx.commit();
        for(Dish d :restaurant1.getDishes()){
            System.out.println(d.getRestaurant());
            System.out.println(d.getName());
            for (Ingredient i :d.getIngredientSet()){
                System.out.println(i.getIngredient());
            }
        }

    }
    public static void deleteWithoutCascade(EntityManager em){

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Restaurant restaurant = em.find(Restaurant.class, 1L);
        Dish dish = em.find(Dish.class, 1L);
        Dish dish1 = em.find(Dish.class, 2L);

        em.remove(dish);
        em.remove(dish1);
        em.remove(restaurant);
        tx.commit();

    }

    private static void deleteWithCascade(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Restaurant restaurant = em.find(Restaurant.class, 1L);
        em.remove(restaurant);
        tx.commit();
    }

    public static void removeOrphanObject(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Restaurant restaurant = em.find(Restaurant.class, 1L);
        restaurant.getDishes().clear();
        tx.commit();
    }

    private static void valueTypeCollectionUpdateTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Restaurant restaurant= em.getReference(Restaurant.class, 1L);
        Dish dishProxy = em.getReference(Dish.class, 1L);

        boolean isLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(restaurant);
        boolean isDishLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(dishProxy);

        System.out.println("is restaurant loaded? : " +isLoaded);
        System.out.println("are dishes loaded? : " + isDishLoaded);
        restaurant.getDishes().remove(0);


        Dish dish = new Dish();
        dish.setName("f");
        dish.setRestaurant(restaurant);
        restaurant.getDishes().add(dish);


        tx.commit();

    }

    private static void manyTomanyTest(EntityManager em){
        EntityTransaction tx = em.getTransaction();




    }

}
