package chap06.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class BuyerProductId  implements Serializable {

    private Buyer buyer;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerProductId that = (BuyerProductId) o;
        return buyer.equals(that.buyer) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer, product);
    }
}
