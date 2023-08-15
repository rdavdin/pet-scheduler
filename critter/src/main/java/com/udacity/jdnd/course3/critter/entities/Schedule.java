package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime time;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Pet pet;
}
