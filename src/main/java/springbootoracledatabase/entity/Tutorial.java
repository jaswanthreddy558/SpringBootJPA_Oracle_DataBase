package springbootoracledatabase.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tutorials")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tutorial implements Serializable {
    private static final long serialversionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "published", nullable = false)
    private boolean published;


    @Column(name = "timestamp")
    private Date createdAt;


    public Tutorial(String title, String description, boolean published, Date dateStamp){
        this.title = title;
        this.description = description;
        this.published = published;
        this.createdAt = dateStamp ;

    }



}
