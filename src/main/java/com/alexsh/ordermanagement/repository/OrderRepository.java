package com.alexsh.ordermanagement.repository;

import com.alexsh.ordermanagement.model.Courier;
import com.alexsh.ordermanagement.model.Customer;
import com.alexsh.ordermanagement.model.Order;
import com.alexsh.ordermanagement.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Repository
public class OrderRepository {

    private final Map<Long, Order> ordersMap = new HashMap<>();

    public OrderRepository() {
        // Initialize with some sample data
        initSampleData();
    }

    private void initSampleData() {
        Customer customer = new Customer(
                1L,
                "Иванов Иван",
                "0123456789",
                "ivanov@example.com",
                15,
                LocalDate.of(2023, 1, 10)
        );

        Courier courier = new Courier(
                1L,
                "Петров Петр",
                "9876543210"
        );

        List<OrderItem> items1 = new ArrayList<>();
        items1.add(new OrderItem(1L, "Пицца \"Маргарита\"", 1, 499));
        items1.add(new OrderItem(2L, "Кока-кола 0.5л", 2, 119));
        items1.add(new OrderItem(3L, "Салат \"Цезарь\"", 1, 349));

        Order order1 = new Order(
                1L,
                "55501",
                "Доставлен",
                LocalDateTime.of(2025, 3, 25, 13, 8),
                "0123456789",
                "—",
                "Прямая",
                "7 мин.",
                "Москва, улица Зеленая, 15, 3",
                customer,
                courier,
                items1,
                1086,
                "Оплата картой онлайн"
        );

        List<OrderItem> items2 = new ArrayList<>();
        items2.add(new OrderItem(4L, "Суши \"Филадельфия\"", 1, 799));
        items2.add(new OrderItem(5L, "Спрайт 0.5л", 1, 119));

        Order order2 = new Order(
                2L,
                "7405",
                "Доставлен",
                LocalDateTime.of(2025, 3, 22, 13, 54),
                "0123456789",
                "—",
                "Прямая",
                "1 мин.",
                "Москва, улица Заречная, 77, 85",
                customer,
                courier,
                items2,
                918,
                "Оплата наличными"
        );

        List<OrderItem> items3 = new ArrayList<>();
        items3.add(new OrderItem(6L, "Бургер \"Классический\"", 2, 450));
        items3.add(new OrderItem(7L, "Картофель фри", 1, 199));
        items3.add(new OrderItem(8L, "Кола 0.5л", 2, 119));

        Order order3 = new Order(
                3L,
                "480",
                "Доставлен",
                LocalDateTime.of(2024, 7, 29, 18, 47),
                "0123456789",
                "—",
                "Прямая",
                "15 мин.",
                "Ижевск, улица Центральная, 56, 57",
                customer,
                courier,
                items3,
                1337,
                "Оплата картой онлайн"
        );

        // Add orders to map
        ordersMap.put(order1.getId(), order1);
        ordersMap.put(order2.getId(), order2);
        ordersMap.put(order3.getId(), order3);
    }

    public List<Order> findAll() {
        return new ArrayList<>(ordersMap.values());
    }

    public Order findById(Long id) {
        return ordersMap.get(id);
    }

    public List<Order> findByCustomerPhone(String phone) {
        return ordersMap.values().stream()
                .filter(order -> order.getCustomerPhone().equals(phone))
                .collect(Collectors.toList());
    }

    public List<Order> findByOrderNumber(String orderNumber) {
        return ordersMap.values().stream()
                .filter(order -> order.getOrderNumber().equals(orderNumber))
                .collect(Collectors.toList());
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId((long) (ordersMap.size() + 1));
        }
        ordersMap.put(order.getId(), order);
        return order;
    }

    public void deleteById(Long id) {
        ordersMap.remove(id);
    }
}