package io.murad.MichelleFajardo_301097601_MTTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String movieName;
    private String country;
    private Integer duration;
    private Integer year;
    private MovieLanguage language;
    private MovieGenre genre;
}
