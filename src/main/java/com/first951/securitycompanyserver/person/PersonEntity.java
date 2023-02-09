package com.first951.securitycompanyserver.person;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

}
