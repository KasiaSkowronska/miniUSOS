package miniUSOS.Classes;

import javax.persistence.*;

/**
 * Created by kosss on 08.05.2017.
 */

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

}
