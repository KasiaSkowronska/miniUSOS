package miniUSOS.Classes;

import miniUSOS.Classes.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kasia on 13.04.2017.
 */

@Entity
@DiscriminatorValue("STUDENT")

public class Student extends User {

    @ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "GROUP_STUDENTS",
        joinColumns = @JoinColumn(name="student_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id"))
    protected Set<Group> groups = new HashSet<>();

    public Set<Group> getGroups() { return groups; }

    public void setGroups(Set<Group> groups) { this.groups = groups; }


}


