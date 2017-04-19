package miniUSOS;

import javax.persistence.*;
import java.util.HashSet;
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
            joinColumns = @JoinColumn(name="groups"),
            inverseJoinColumns = @JoinColumn(name = "students"))
    protected Set<Student> students = new HashSet<>();


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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
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

    @OneToOne(mappedBy = "group", optional = false)
    private Course course;
}
