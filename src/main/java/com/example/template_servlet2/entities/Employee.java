package com.example.template_servlet2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "EMP.FIND_ALL", query = "select e from Employee e"),
        @NamedQuery(name = "EMP.FIND_BY_ID", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "EMP.FIND_BY_EMAIL", query = "select e from Employee e where e.email = :email"),
        @NamedQuery(name = "EMP.FIND_BY_NAME", query = "select e from Employee e where e.firstName like :firstname or e.lastName like :lastname")
})
@ToString
public class Employee {
    @Id
    @Column(name = "employeeNumber", nullable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private Integer reportsTo;
    private String jobTitle;
    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;
    private String password;
}
