package chap06.onetoone;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private LockerUser lockerUser;

    public Locker(String name) {
        this.name = name;
    }
}
