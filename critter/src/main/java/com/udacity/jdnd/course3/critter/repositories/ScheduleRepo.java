package com.udacity.jdnd.course3.critter.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;


@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>{
    @Query("SELECT s FROM Schedule s WHERE :pet MEMBER OF s.pets")
    public List<Schedule> findByAPet(Pet pet);

    //TO DO: Should investigate deeply about findByPetsIn(Set<Pet> pets)
    // public Set<Schedule> findByPetsIn(Set<Pet> pets);
    // public List<Schedule> findByPetsIn(List<Pet> pets);
    
    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id IN :petIds")
    public List<Schedule> findByPetsQuery(List<Long> petIds);

    @Query("SELECT s FROM Schedule s WHERE :employee MEMBER OF s.employees")
    public List<Schedule> findByAEmployee(Employee employee);

    public List<Schedule> findByDate(LocalDate date);
    
}
