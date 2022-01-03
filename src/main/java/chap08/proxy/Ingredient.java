package chap08.proxy;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ingredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredient;
}
