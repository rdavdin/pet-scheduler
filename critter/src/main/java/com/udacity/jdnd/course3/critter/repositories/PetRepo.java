package com.udacity.jdnd.course3.critter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>{
    public List<Pet> findByOwner(Customer owner);

    @Query("SELECT p.owner FROM Pet p WHERE p.id = :id")
    public Customer findOwnerByPetId(Long id);
}
