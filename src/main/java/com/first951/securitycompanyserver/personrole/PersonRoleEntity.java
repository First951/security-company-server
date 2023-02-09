package com.first951.securitycompanyserver.personrole;

import com.first951.securitycompanyserver.person.PersonEntity;
import com.first951.securitycompanyserver.role.RoleEntity;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "person_role")
public class PersonRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "period_from", nullable = false)
    private Date periodFrom;

    @Column(name = "peroid_to")
    private Date periodTo;

}
