package chap07.join_strategy;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;

    @Builder
    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
