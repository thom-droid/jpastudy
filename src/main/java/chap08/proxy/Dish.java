package chap08.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@NamedQuery(name = "Dish.findByIngredient",
        query = "select d from Dish d join d.ingredientSet i where i.ingredient = :ingredient")
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TEST_ID", nullable = false)
    private Set<Ingredient> ingredientSet = new HashSet<>();

}
