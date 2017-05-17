package miniUSOS.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kosss on 08.05.2017.
 */

@Entity
@DiscriminatorValue("LECTURER")
public class Lecturer extends User {

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @OneToMany(targetEntity = Group.class, mappedBy = "lecturer", cascade = CascadeType.ALL)
    protected Set<Group> groups = new HashSet<>();

}
