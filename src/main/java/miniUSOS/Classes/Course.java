package miniUSOS.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosss on 19.04.2017.
 */

//ID NAME CODE FACULTY ECTS PLACES SYLABUS GROPUS

@Entity
@Table(name="COURSES")
public class Course {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "code")
    protected String code;

    @Column(name = "faculty")
    protected String faculty;

    @Column(name = "ects")
    protected int ects;

    @Column(name = "places")
    protected int places;

    @Column(name = "sylabus")
    protected String sylabus;

    @OneToMany(targetEntity = Group.class, mappedBy = "course", cascade = CascadeType.ALL)
    protected List<Group> groups = new ArrayList<>();





    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


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

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getSylabus() {
        return sylabus;
    }

    public void setSylabus(String sylabus) {
        this.sylabus = sylabus;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return name;
    }

}
