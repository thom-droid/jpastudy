package chap06.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BuyerProduct {

    @Id
    @GeneratedValue
    @Column(name = "BUYERPRODUCT_ID")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;


}
