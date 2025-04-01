package com.alexsh.ordermanagement.repository;

import com.alexsh.ordermanagement.model.Courier;
import com.alexsh.ordermanagement.model.Customer;
import com.alexsh.ordermanagement.model.Order;
import com.alexsh.ordermanagement.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {

    private final Map<Long, Customer> customersMap = new HashMap<>();

    public CustomerRepository() {
        // Initialize with sample data
        initSampleData();
    }

    private void initSampleData() {
        Customer customer1 = new Customer(
                1L,
                "Иванов Иван",
                "0123456789",
                "ivanov@example.com",
                15,
                LocalDate.of(2023, 1, 10)
        );

        Customer customer2 = new Customer(
                2L,
                "Петрова Анна",
                "8765432100",
                "petrova@example.com",
                7,
                LocalDate.of(2023, 5, 22)
        );

        // Add to map
        customersMap.put(customer1.getId(), customer1);
        customersMap.put(customer2.getId(), customer2);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customersMap.values());
    }

    public Customer findById(Long id) {
        return customersMap.get(id);
    }

    public Customer findByPhone(String phone) {
        return customersMap.values().stream()
                .filter(customer -> customer.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> findByName(String name) {
        return customersMap.values().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId((long) (customersMap.size() + 1));
        }
        customersMap.put(customer.getId(), customer);
        return customer;
    }

    public void deleteById(Long id) {
        customersMap.remove(id);
    }
}