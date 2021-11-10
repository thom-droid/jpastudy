package chap07.mapped_super_class;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
