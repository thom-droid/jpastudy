package chap07.mapped_super_class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Seller extends BaseEntity{

    private String shopName;
}
