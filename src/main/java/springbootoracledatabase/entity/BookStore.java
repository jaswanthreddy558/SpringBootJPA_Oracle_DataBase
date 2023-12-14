package springbootoracledatabase.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "USER_INFORMATION")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookStore implements Serializable {
    private static final long serialversionUID = 1L;

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
    private LocalDate createdAt;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "roles")
    private String roles;


    public BookStore(String title, String description, boolean published, LocalDate createdAt, String username, String password, String roles) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.createdAt = createdAt;
        this.username = username;
        this.roles = roles;

    }


    public BookStore(BookStore tutorial) {
    }


}
