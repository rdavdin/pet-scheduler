package com.udacity.jdnd.course3.critter.controllers.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = convertDTO2Schedule(scheduleDTO);
            scheduleService.save(schedule);
            return convertSchedule2DTO(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return schedules.stream().map(s -> convertSchedule2DTO(s)).toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getSchedulesByPetId(petId);
        return schedules.stream().map(s -> convertSchedule2DTO(s)).toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployeeId(employeeId);
        return schedules.stream().map(s -> convertSchedule2DTO(s)).toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCustomerId(customerId);
        return schedules.stream().map(s -> convertSchedule2DTO(s)).toList();
    }

    //helpers
    private Schedule convertDTO2Schedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Employee> employees= scheduleDTO.getEmployeeIds().stream().map(id -> employeeService.getEmployeeById(id)).toList();
        List<Pet> pets = scheduleDTO.getPetIds().stream().map(id -> petService.getPetById(id)).toList();
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return schedule;
    }

    private ScheduleDTO convertSchedule2DTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> employeeIds = schedule.getEmployees().stream().map(s -> s.getId()).toList();
        List<Long> petIds = schedule.getPets().stream().map(p -> p.getId()).toList();
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }
}
