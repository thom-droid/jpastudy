package chap05.entity_relation;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @Column(name ="COURSE_ID")
    private String id;

    private String name;
    private Integer seats;

    public Course(String id, String name, Integer seats) {
        super();
        this.id = id;
        this.name = name;
        this.seats = seats;
    }

    @OneToMany(mappedBy = "course")
    private List<Student> students = new ArrayList<Student>();

}