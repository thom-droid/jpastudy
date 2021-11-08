package chap06.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name ="TEAM_ID")
    private Team team;

//    public void setTeam(Team team){
////        this.team = team;
//
//        // team에 현재 멤버가 없을 때에만 추가
//        if(!team.getMembers().contains(this))
//            team.getMembers().add(this);
//    }


    public Member(String userName) {
        this.userName = userName;
    }
}
