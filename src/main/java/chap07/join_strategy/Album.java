package chap07.join_strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@ToString
@Entity
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;

}
