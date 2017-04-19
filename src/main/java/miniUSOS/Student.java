package miniUSOS;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kasia on 13.04.2017.
 */

@Entity
@Table(name = "STUDENT")

public class Student {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_STUDENTS",
            joinColumns = @JoinColumn(name="students"),
            inverseJoinColumns = @JoinColumn(name = "groups"))
    protected Set<Group> groups = new HashSet<>();

    public Set<Group> getGroups() { return groups; }

    public void setGroups(Set<Group> groups) { this.groups = groups; }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


