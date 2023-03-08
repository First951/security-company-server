package com.first951.securitycompanyserver.schema.markplan;

import com.first951.securitycompanyserver.schema.schedule.ScheduleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "mark_plan")
@Getter
@Setter
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
