package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.schema.organization.Organization;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "places")
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization", nullable = false)
    private Organization organization;

    @Column(name = "name", nullable = false)
    private String name;

}
