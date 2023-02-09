package com.first951.securitycompanyserver.markregister;

import com.first951.securitycompanyserver.markplan.MarkPlanEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "mark_register")
public class MarkRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "mark_plan_id", nullable = false)
    private MarkPlanEntity markPlan;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "type", nullable = false)
    private MarkRegisterType type;

    @Column(name = "comment", nullable = false)
    private String comment;


}
