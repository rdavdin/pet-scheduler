package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    private PetType petType;

    private LocalDate birthDate;

    @Column(length = 512)
    private String notes;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
