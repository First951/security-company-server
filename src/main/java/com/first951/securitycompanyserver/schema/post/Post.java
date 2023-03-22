package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.schema.organization.Organization;
import com.first951.securitycompanyserver.schema.place.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "organization", nullable = false)
    private Organization organization;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment")
    private String comment;

}
