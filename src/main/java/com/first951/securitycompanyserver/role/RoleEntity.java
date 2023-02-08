package com.first951.securitycompanyserver.role;

import jakarta.persistence.*;

@Entity
@Table(name = "roless")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

}
