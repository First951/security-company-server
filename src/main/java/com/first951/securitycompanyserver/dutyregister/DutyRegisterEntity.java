package com.first951.securitycompanyserver.dutyregister;

import com.first951.securitycompanyserver.person.PersonEntity;
import com.first951.securitycompanyserver.post.PostEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "duty_register")
public class DutyRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "guard_id", nullable = false)
    private PersonEntity guard;

    @Column(name = "begin", nullable = false)
    private Timestamp begin;

    @Column(name = "end", nullable = false)
    private Timestamp end;

}
