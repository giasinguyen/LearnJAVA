package model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "albums")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album implements Serializable {
    @Id
    @Column(name = "album_id")
    private String id;
    private String title;
    private double price;
    @Column(name = "year_of_release")
    private int yearOfRelease;
    @Column(name = "download_link")
    private String downloadLink;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ToString.Exclude
    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new LinkedHashSet<>();
}
