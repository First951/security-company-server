package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.schema.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "timestamp_begin", nullable = false)
    private Timestamp begin;

    @Column(name = "timestamp_end", nullable = false)
    private Timestamp end;

}
