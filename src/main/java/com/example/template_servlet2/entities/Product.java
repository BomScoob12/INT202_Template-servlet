package com.example.template_servlet2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQueries({
        @NamedQuery(name = "PRODUCT.FIND_ALL", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "PRODUCT.COUNT", query = "SELECT count(p) as count FROM Product p")
})
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Double buyPrice; //cost
    @Column(name = "MSRP")
    private Double price; // MSRP Manufacturer Suggestion Retail Price
}
