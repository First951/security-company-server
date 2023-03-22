package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.schema.post.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "places")
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "place")
    private List<Post> posts;

}
