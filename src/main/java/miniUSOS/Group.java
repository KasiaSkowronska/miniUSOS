package miniUSOS;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kosss on 19.04.2017.
 */

@Entity
@Table(name="GROUPS")
public class Group {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "NAME")
    protected String name;

    @ManyToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_STUDENTS",
        joinColumns = @JoinColumn(name="gid"),
        inverseJoinColumns = @JoinColumn(name = "sid"))
    protected List<Student> students = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "COURSE")
    protected Course course;

}
