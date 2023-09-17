package com.udacity.jdnd.course3.critter.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepo;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepo scheduleRepo;
    @Autowired
    PetService petService;
    @Autowired
    EmployeeService employeeService;

    public Schedule save(Schedule sch){
        return scheduleRepo.save(sch);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepo.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId){
        Pet pet = petService.getPetById(petId);
        return scheduleRepo.findByAPet(pet);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long empId){
        Employee employee = employeeService.getEmployeeById(empId);
        return scheduleRepo.findByAEmployee(employee);
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        List<Pet> pets = petService.getAllByOwnerId(customerId);
        List<Long> petIds = pets.stream().map(p -> p.getId()).toList();
        return scheduleRepo.findByPetsQuery(petIds);
    }
}
