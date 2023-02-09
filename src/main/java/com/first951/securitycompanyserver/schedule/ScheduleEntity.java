package com.first951.securitycompanyserver.schedule;

import com.first951.securitycompanyserver.post.PostEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "timestamp_begin", nullable = false)
    private Timestamp begin;

    @Column(name = "timestamp_end", nullable = false)
    private Timestamp end;

}
