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
@Table(name="COURSES")
public class Course {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ects")
    protected int ects;

    @Column(name = "abundance")
    protected int abundance;

    @Column(name = "sylabus")
    protected String sylabus;

    @OneToMany(targetEntity = Group.class, mappedBy = "course", cascade = CascadeType.ALL)
    protected List<Group> groups = new ArrayList<>();

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

    public int getAbundance() {
        return abundance;
    }

    public void setAbundance(int abundance) {
        this.abundance = abundance;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
