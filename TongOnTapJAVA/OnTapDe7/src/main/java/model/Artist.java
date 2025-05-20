package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artist implements Serializable {
    @Id
    @Column(name = "artist_id")
    private String id;
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String url;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "albums_artists", joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums = new LinkedHashSet<>();
}
