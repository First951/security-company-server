package ru.first.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 *Сущность Объекты из БД
 */
@Entity
@Data
@Table(name="objects", schema="public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ObjectOrganization {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_organization")
    private Organization organization;

    @Column(name="name")
    private String name;

    @Column(name="adress")
    private String adress;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "object", orphanRemoval = true)
    private List<PostOfObjectsOrganization> posts;
}
