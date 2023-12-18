package com.example.template_servlet2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "offices")
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "OFFICE.FIND_ALL", query = "select o from Office o"),
        @NamedQuery(name = "OFFICE.FIND_OFFICE_EMPLOYEE", query = "select o.employees from Office o where o.id = :id"),
        @NamedQuery(name = "OFFICE.FIND_OFFICE_BY_ID", query = "select o from Office o where o.id = :id")
})
@ToString(exclude = "employees")
public class Office {
    @Id
    @Column(name = "officeCode", nullable = false)
    private String id;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;

    @OneToMany(mappedBy = "officeCode")
    List<Employee> employees;
}
