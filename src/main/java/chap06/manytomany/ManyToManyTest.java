package chap06.manytomany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ManyToManyTest {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        testSave(em);
        testFind(em);

    }

    public static void testSave(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Buyer buyer = new Buyer();
        buyer.setBuyerName("hyeonsu");
        em.persist(buyer);

        Product productA = new Product();
        productA.setName("productA");
        em.persist(productA);

        BuyerProduct buyerProduct = new BuyerProduct();
        buyerProduct.setBuyer(buyer);
        buyerProduct.setProduct(productA);
        buyerProduct.setOrderAmount(2);
        em.persist(buyerProduct);
        tx.commit();
    }
    public static void testFind(EntityManager em){

        BuyerProductId buyerProductId = new BuyerProductId();
        Buyer buyer = em.find(Buyer.class, 1L);
        Product product = em.find(Product.class, 1L);

        buyerProductId.setBuyer(buyer);
        buyerProductId.setProduct(product);

        BuyerProduct buyerProduct = em.find(BuyerProduct.class, buyerProductId);

        Buyer findBuyer = buyerProduct.getBuyer();
        Product findProduct = buyerProduct.getProduct();

        System.out.println(findBuyer.getBuyerName());
        System.out.println(findProduct.getName());
        System.out.println(buyerProduct.getOrderAmount());


    }

}
