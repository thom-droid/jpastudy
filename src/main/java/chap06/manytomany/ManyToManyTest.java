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
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Product product1 = new Product();
        product1.setName("product1");
        em.persist(product1);

        Buyer buyer = new Buyer();
        buyer.setBuyerName("buyer1");
        buyer.addProduct(product1);
        em.persist(buyer);

        tx.commit();

        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        Buyer findBuyer = em.find(Buyer.class, 1L);
        List<Product> products = findBuyer.getProducts(); // 객체 그래프 탐색

        for(Product p: products){
            System.out.println(p.getName());
        }

        Product findProduct = em.find(Product.class, 1L);
        List<Buyer> buyers = findProduct.getBuyers();

        for(Buyer b : buyers){
            System.out.println(b.getBuyerName());
        }


    }

}
