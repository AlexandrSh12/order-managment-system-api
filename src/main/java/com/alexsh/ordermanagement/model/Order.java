package com.alexsh.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String orderNumber;
    private String status;
    private LocalDateTime createdAt;
    private String customerPhone;
    private String compensation;
    private String deliveryType;
    private String delay;
    private String deliveryAddress;
    private Customer customer;
    private Courier courier;
    private List<OrderItem> items;
    private double totalPrice;
    private String paymentMethod;

    // Удобные методы для получения отформатированной даты/времени
    public String getCreatedDate() {
        return createdAt.toLocalDate().toString().replace("-", ".");
    }

    public String getCreatedTime() {
        return createdAt.toLocalTime().toString().substring(0, 5); // Format as HH:MM
    }
}