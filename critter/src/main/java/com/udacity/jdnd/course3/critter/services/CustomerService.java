package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer getCustomerById(Long id){
        return customerRepo.getReferenceById(id);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public void delete(Long id){
        Customer customer = this.getCustomerById(id);
        customerRepo.delete(customer);
    }
}
