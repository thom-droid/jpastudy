package chap05.entity_relation;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @Column(name="STUDENT_ID")
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name="COURSE_ID")
    private Course course;

    public void setCourse(Course course) {
        if (this.course != null)
            this.course.getStudents().remove(this);
        this.course = course;
        course.getStudents().add(this);
    }

    public Student(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }




}