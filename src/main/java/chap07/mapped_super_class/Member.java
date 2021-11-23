package chap07.mapped_super_class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "TEAM_ID", nullable = false)
    private Team team;

    public void addTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }


}
