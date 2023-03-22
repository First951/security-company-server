package com.first951.securitycompanyserver.schema.mark;

import com.first951.securitycompanyserver.schema.mark.type.MarkType;
import com.first951.securitycompanyserver.schema.person.Person;
import com.first951.securitycompanyserver.schema.schedule.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "marks")
@Getter
@Setter
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "person")
    private Person person;

    @Column(name = "plan_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date planTimestamp;

    @Column(name = "fact_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factTimestamp;

    @Column(name = "type")
    private MarkType type;

    @Column(name = "comment")
    private String comment;

}
