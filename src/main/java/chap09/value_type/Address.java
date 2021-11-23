package chap09.value_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    String city;
    String street;
    String state;

    @Embedded
    ZipCode zipCode;

}
