package com.first951.securitycompanyserver.schema.organization;

import com.first951.securitycompanyserver.schema.post.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "organizations")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "organization")
    private List<Post> posts;

}
