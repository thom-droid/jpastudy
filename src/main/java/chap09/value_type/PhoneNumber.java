package chap09.value_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PhoneNumber {

    private String areaCode;
    private String localNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provider_id")
    PhoneServiceProvider provider;

}
