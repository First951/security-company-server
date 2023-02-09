package com.first951.securitycompanyserver.object;

import com.first951.securitycompanyserver.organization.OrganizationEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "object")
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;

    @Column(name = "name", nullable = false)
    private String name;

}
