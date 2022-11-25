package ru.first.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *Сущность График дежурств из БД
 */
@Entity
@Data
@Table(name="timeline_duty", schema="public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TimelineDuty {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_post")
    private PostOfObjectsOrganization post;

    @Column(name="date_work")
    private Date dateWork;

    @Column(name="start_work")
    private Time startWork;

    @Column(name="end_work")
    private Time endWork;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timelineDuty", orphanRemoval = true)
    private List<MarkingPlan> markingPlanes;
}
