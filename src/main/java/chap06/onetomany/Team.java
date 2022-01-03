package chap06.onetomany;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

//    public void addMember(Member member){
//        this.members.add(member);
//        if(member.getTeam()!=this)
//            member.setTeam(this);
//    }

    public Team(String name) {
        this.name = name;
    }
}
