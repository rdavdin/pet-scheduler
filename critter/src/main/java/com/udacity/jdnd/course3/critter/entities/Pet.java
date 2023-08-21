package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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

    @Enumerated(EnumType.STRING)
    private PetType type;

    private LocalDate birthDate;

    @Column(length = 512)
    private String notes;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer owner;

    
    // @ManyToMany(mappedBy = "pets", fetch = FetchType.LAZY)
    // private List<Schedule> schedules;

    @Override
    public String toString() {
        return "Pet [id=" + id + ", name=" + name + ", petType=" + type + ", birthDate=" + birthDate + ", notes="
                + notes + ", owner=" + owner+"]";
    }


    public Pet(Long id, String name, PetType type, LocalDate birthDate, String notes, Customer owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }    
}
