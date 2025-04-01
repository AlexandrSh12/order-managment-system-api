package com.alexsh.ordermanagement.service;

import com.alexsh.ordermanagement.model.Order;
import com.alexsh.ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> searchOrders(String searchType, String searchValue) {
        if (searchValue == null || searchValue.isEmpty()) {
            return List.of();
        }

        switch (searchType) {
            case "customerPhone":
                return orderRepository.findByCustomerPhone(searchValue);
            case "orderId":
                try {
                    Long orderId = Long.parseLong(searchValue);
                    Order order = orderRepository.findById(orderId);
                    return order != null ? List.of(order) : List.of();
                } catch (NumberFormatException e) {
                    return List.of();
                }
            case "orderNumber":
                return orderRepository.findByOrderNumber(searchValue);
            default:
                return List.of();
        }
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        if (orderRepository.findById(id) != null) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}