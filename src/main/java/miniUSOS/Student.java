package miniUSOS;

import javax.persistence.*;

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




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    }


