package com.udacity.jdnd.course3.critter.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    private String phoneNumber;

    @Column(length=512)
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    
}
