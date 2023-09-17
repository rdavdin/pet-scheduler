package com.udacity.jdnd.course3.critter.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetService petService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = customerService.save(convertDTO2Customer(customerDTO));
        return convertCustomer2DTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map((c) -> convertCustomer2DTO(c)).toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer owner = petService.getOwnerById(petId);
        if(owner != null){
            return convertCustomer2DTO(owner);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found");
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable long customerId){
        try {
            customerService.delete(customerId);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer Not Found", e);
        }
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee e = employeeService.save(convertDTO2Employee(employeeDTO));
        return convertEmployee2DTO(e);
    }

    // @PostMapping("/employee/{employeeId}")
    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        try {
            Employee e = employeeService.getEmployeeById(employeeId);
            return convertEmployee2DTO(e);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee Not Found", e);
        }
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        try {
            Employee e = employeeService.getEmployeeById(employeeId);
            e.setDaysAvailable(daysAvailable);
            employeeService.save(e);    
        } catch (Exception e) {
            System.out.println("Cannot find the employee with id "+employeeId);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee Not Found", e);
        }
    }

    @Operation(summary = "Check which employees availability. No data is affected by this method.")
    @PostMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.checkAvailabilityEmployees(employeeDTO.getSkills(), employeeDTO.getDate());
        return employees.stream().map(e -> convertEmployee2DTO(e)).toList();
    }

    //helpers
    private Customer convertDTO2Customer(CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        return customer;
    }

    private CustomerDTO convertCustomer2DTO(Customer customer){
        Set<Pet> pets = customer.getPets();
        List<Long> petIds = pets.stream().map(p -> p.getId()).collect(Collectors.toList());
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getNotes(), petIds);
        return customerDTO;
    }

    private Employee convertDTO2Employee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private EmployeeDTO convertEmployee2DTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
}
