package ru.first.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность Организация из БД
 */

@Entity
@Data
@Table(name="organization", schema="public")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="adress")
    private String adress;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", orphanRemoval = true)
    private List<ObjectOrganization> objects;
}
