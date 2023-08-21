package com.udacity.jdnd.course3.critter.repositories;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    @Query("SELECT e FROM Employee e JOIN e.skills WHERE :day MEMBER OF e.daysAvailable")
    public List<Employee> checkAvailabilityEmployees(DayOfWeek day);
}
