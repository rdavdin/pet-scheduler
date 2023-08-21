package com.udacity.jdnd.course3.critter.controllers.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        try {
            Pet pet = convertDTO2Pet(petDTO);
            PetDTO rPetDTO = convertPet2DTO(petService.save(pet));
            return rPetDTO;
        } catch (Exception e) {
            System.out.println("Owner required! But not found it.");
            return null;
        }
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        try {
            Pet pet = petService.getPetById(petId);
            return convertPet2DTO(pet);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return pets.stream().map(p -> convertPet2DTO(p)).toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getAllByOwnerId(ownerId);
        return pets.stream().map(p -> convertPet2DTO(p)).toList();
    }

    //helpers
    private PetDTO convertPet2DTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private Pet convertDTO2Pet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setOwner(customerService.getCustomerById(petDTO.getOwnerId()));
        return pet;
    }
}
