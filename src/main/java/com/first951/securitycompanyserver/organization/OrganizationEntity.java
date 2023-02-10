package com.first951.securitycompanyserver.organization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization")
@Getter
@Setter
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "name", nullable = false)
    private String name;

}
