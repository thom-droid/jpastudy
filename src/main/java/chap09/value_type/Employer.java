package chap09.value_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    Period workPeriod;

    @Embedded
    Address homeAddress;

    @Embedded
    PhoneNumber phoneNumber;

}
