package com.udacity.jdnd.course3.critter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    
}
