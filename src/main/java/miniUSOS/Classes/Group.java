package miniUSOS.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosss on 19.04.2017.
 */

// ID NUMBER TYPE TUTOR TIME

@Entity
@Table(name="GROUPS")
public class Group {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "number")
    protected Integer number;

    @Column(name = "type")
    protected String type;

    @Column(name = "tutor")
    protected String tutor;

    @Column(name = "time")
    protected String time;

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_STUDENTS",
        joinColumns = @JoinColumn(name="group_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    protected List<Student> students = new ArrayList<>();

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_LECTURERS",
            joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id"))
    protected List<Lecturer> lecturers = new ArrayList<>();

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "COURSE")
    protected Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + number + '\'' +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


}
