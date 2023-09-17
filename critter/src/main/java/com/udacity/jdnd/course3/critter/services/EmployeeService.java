package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ScheduleRepo scheduleRepo;

    public Employee save(Employee e){
        return employeeRepo.save(e);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepo.getReferenceById(id);
    }

    public List<Employee> checkAvailabilityEmployees(Set<EmployeeSkill> skills, LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> employees = employeeRepo.checkAvailabilityEmployees(dayOfWeek);
        Set<Employee> scheduledEmployees = getEmployeesScheduledOnDate(date);
        return employees.stream().filter(e -> {
            if(scheduledEmployees.contains(e)){
                return  false;
            }
            boolean collected = true;
            for(EmployeeSkill s : skills){
                if(!e.getSkills().contains(s)){
                    collected = false;
                    break;
                }
            }
            return collected;
        }).toList();
    }

    private Set<Employee> getEmployeesScheduledOnDate(LocalDate date){
        Set<Employee> employees = new HashSet<>();
        List<Schedule> schedules = scheduleRepo.findByDate(date);
        for (Schedule schedule: schedules) {
            for(Employee e : schedule.getEmployees()){
                employees.add(e);
            }
        }
        return employees;
    }
}
