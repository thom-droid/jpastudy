package chap09.value_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class ZipCode {

    String zip;
    String plusFour;
}
