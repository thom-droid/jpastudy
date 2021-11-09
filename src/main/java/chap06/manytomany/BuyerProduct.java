package chap06.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(BuyerProductId.class)
public class BuyerProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;


}
