package chap07.composite_key;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Child {

    @Id
    private String id;

    @ManyToOne
    @JoinColumns({
      @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
      @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENT_ID2")
    })
    private Parent parent;

}
