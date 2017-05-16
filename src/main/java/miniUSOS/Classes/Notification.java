package miniUSOS.Classes;

import javax.persistence.*;

/**
 * Created by Kasia on 16.05.2017.
 */
@Entity
@Table(name="NOTIFICATIONS")
public class Notification {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "NOTIFICATION_USER")
    protected User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
