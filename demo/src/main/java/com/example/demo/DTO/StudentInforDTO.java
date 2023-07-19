package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class StudentInforDTO {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate dob;
    private String email;
    private AddressDTO address;




}
