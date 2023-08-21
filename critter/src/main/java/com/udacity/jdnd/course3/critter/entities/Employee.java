package com.udacity.jdnd.course3.critter.entities;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    // @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private  List<Schedule> schedules;
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", skills=" + skills + ", daysAvailable=" + daysAvailable
                + "]";
    }


    
}
