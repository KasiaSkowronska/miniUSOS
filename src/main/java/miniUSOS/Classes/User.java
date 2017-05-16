package miniUSOS.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosss on 08.05.2017.
 */
@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Inheritance
@DiscriminatorColumn(name="USER_TYPE")
@Table(name="USER")
public abstract class User {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Notification.class, mappedBy = "user", cascade = CascadeType.ALL)
    protected List<Notification> notifications = new ArrayList<>();

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
