package com.udacity.jdnd.course3.critter.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;

@Service
public class PetService {
    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerService customerService;

    public Pet save(Pet p){
        try {
            Customer owner = p.getOwner();
            owner.addPet(p);
            return petRepo.save(p);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Pet getPetById(Long id){
        return petRepo.getReferenceById(id);
    }

    public List<Pet> getAllPets(){
        return petRepo.findAll();
    }

    public List<Pet> getAllByOwnerId(Long ownerId){
        try {
            Customer owner = customerService.getCustomerById(ownerId);
            return petRepo.findByOwner(owner);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Customer getOwnerById(Long id){
        try {
            return petRepo.findOwnerByPetId(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
