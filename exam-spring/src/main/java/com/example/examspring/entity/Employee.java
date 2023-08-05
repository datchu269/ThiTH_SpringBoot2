package com.example.examspring.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "employee_table")
@Data
@SuperBuilder
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "employee_name", nullable = false)
    private String name;
    @Column(name = "employee_birth_day", nullable = false)
    private String birthDay;
    @Column(name = "employee_address")
    private String address;
}
