package com.udacity.jdnd.course3.critter.entities;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  List<Schedule> schedules;

    public Employee() {
    }
}
