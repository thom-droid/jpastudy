package chap06.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUYER_ID")
    private Long id;

    private String buyerName;

    @ManyToMany
    @JoinTable(name = "BUYER_PRODUCT",
            joinColumns = @JoinColumn(name = "BUYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product){
        this.products.add(product);
        product.getBuyers().add(this);
    }


}
