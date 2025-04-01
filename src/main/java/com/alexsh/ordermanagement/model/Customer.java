package com.alexsh.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Integer deliveryCount;
    private LocalDate registrationDate;

    // Удобный метод для получения отформатированной даты регистрации
    public String getFormattedRegistrationDate() {
        return registrationDate.toString().replace("-", ".");
    }
}