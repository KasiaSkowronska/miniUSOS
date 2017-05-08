package miniUSOS.Classes;

import javax.persistence.*;

/**
 * Created by Kasia on 04.05.2017.
 */

@Entity
@Table(name="REQUESTS")

public class Request {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "student")
    protected Student student;

    @Column(name = "group")
    protected Group group;
}
