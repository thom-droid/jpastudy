package chap07.mapped_super_class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
@Entity
public class Member extends BaseEntity{

    private String email;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void addTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    @ElementCollection
    @CollectionTable(name ="MEMBER_NICKNAMES", joinColumns = @JoinColumn(name="MEMBER_ID"))
    @Column(name = "NICKNAME")
    private Set<String> nickNames = new HashSet<>();


}
