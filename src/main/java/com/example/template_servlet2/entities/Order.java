package com.example.template_servlet2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Integer orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customer;
}
