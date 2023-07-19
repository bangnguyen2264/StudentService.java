package com.example.demo.DTO;

import com.example.demo.Entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class CLassDetailsDTO {
    private Long id;
    private String ClassName;
    private List<Student> students;
}
