package com.udacity.jdnd.course3.critter.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
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
    
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Pet> pets = new HashSet<>();

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    
    public void addPet(Pet pet) {
        if(pet != null && !this.pets.contains(pet)){
            this.pets.add(pet);
            pet.setOwner(this);
        }
    }

    public void remove(Pet pet){
        if(this.pets.remove(pet)){
            pet.setOwner(null);
        }
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", notes=" + notes + ", pets="
                + "]";
    }
}
