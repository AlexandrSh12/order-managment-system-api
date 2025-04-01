package com.alexsh.ordermanagement.service;

import com.alexsh.ordermanagement.model.Customer;
import com.alexsh.ordermanagement.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public List<Customer> searchCustomers(String searchType, String searchValue) {
        if (searchValue == null || searchValue.isEmpty()) {
            return List.of();
        }

        switch (searchType) {
            case "customerPhone":
                Customer customer = customerRepository.findByPhone(searchValue);
                return customer != null ? List.of(customer) : List.of();
            case "customerName":
                return customerRepository.findByName(searchValue);
            default:
                return List.of();
        }
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (customerRepository.findById(id) != null) {
            customer.setId(id);
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}