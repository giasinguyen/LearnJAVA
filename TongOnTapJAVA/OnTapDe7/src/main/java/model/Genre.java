package model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Genre implements Serializable {
    @Id
    @Column(name = "genre_id")
    private String id;
    private String name;
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "genre")
    private Set<Album> albums = new LinkedHashSet<>();
}
