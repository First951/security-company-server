package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.schema.person.Person;
import com.first951.securitycompanyserver.schema.post.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post", nullable = false)
    private Post post;

    @Column(name = "timestamp_begin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    @Column(name = "timestamp_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

}
