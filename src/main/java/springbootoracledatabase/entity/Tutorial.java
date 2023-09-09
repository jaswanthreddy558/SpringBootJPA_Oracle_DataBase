package springbootoracledatabase.entity;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USER_INFORMATION")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tutorial implements Serializable {
    private static final long serialversionUID = 1L;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CONTACT_ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "timestamp")
    private Date createdAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "roles")
    private String roles;


    public Tutorial(String title, String description, boolean published, Date createdAt, String username, String password, String roles) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.createdAt = createdAt;
        this.username = username;
        this.password = PASSWORD_ENCODER.encode(password);
        this.roles = roles;

    }


    public Tutorial(Tutorial tutorial) {
    }


}
