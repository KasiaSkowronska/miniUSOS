package miniUSOS.Classes;

import javax.persistence.*;

/**
 * Created by Kasia on 15.05.2017.
 */

@Entity
@Table(name="REQUESTS")

public class Request {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    @ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "REQUEST_GROUP")
    protected Group group;

    @ManyToOne(targetEntity = Student.class, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "REQUEST_student")
    protected Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
