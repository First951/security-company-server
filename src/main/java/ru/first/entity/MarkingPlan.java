package ru.first.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 *Сущность План отметок из БД
 */

@Entity
@Data
@Table(name="marking_plan", schema="public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MarkingPlan {

       @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name= "increment", strategy= "increment")
        private Long id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="id_timeline_duty")
        private TimelineDuty timelineDuty;

        @Column(name="time_mark")
        private Time timeMark;


}
