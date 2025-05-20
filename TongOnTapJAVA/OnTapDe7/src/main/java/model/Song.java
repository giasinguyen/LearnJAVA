package model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "songs")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song implements Serializable {
    @Id
    @Column(name = "song_id")
    private String id;
    private String name;
    @Column(name = "runtime")
    private String runTime;
    private String lyric;
    @Column(name = "file_link")
    private String fileLink;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "albums_songs", joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums = new LinkedHashSet<>();
}
