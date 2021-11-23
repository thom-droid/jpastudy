package chap09.value_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PhoneServiceProvider {

    @Id @GeneratedValue
    private Long id;

    private String name;


}
