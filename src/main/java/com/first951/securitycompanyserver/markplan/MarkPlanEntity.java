package com.first951.securitycompanyserver.markplan;

import com.first951.securitycompanyserver.schedule.ScheduleEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "mark_plan")
public class MarkPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

}
