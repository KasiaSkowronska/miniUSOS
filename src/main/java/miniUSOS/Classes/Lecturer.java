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

    @ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "GROUP_LECTURERS",
            joinColumns = @JoinColumn(name="lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    protected Set<Group> groups = new HashSet<>();

}
