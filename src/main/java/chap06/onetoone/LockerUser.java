package chap06.onetoone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class LockerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCKER_USER_ID")
    private Long id;

    private String userName;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public LockerUser(String userName) {
        this.userName = userName;
    }

    public void setLocker(Locker locker){
        this.locker = locker;
    }
}
