package com.first951.securitycompanyserver.post;

import com.first951.securitycompanyserver.object.ObjectEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "object_id", nullable = false)
    private ObjectEntity object;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "address", nullable = false)
    private String address;

}
