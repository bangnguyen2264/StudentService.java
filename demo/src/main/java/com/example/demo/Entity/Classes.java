package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "classes")
public class Classes {
    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator ="class_sequence"
    )
    private Long id;
    private String ClassName;
    @OneToMany(mappedBy = "classes")
    private List<Student> students;

    public Classes(String className) {
        ClassName = className;
    }

    public Classes(Long id, String ClassName, List<Student> students) {
        this.id = id;
        this.ClassName = ClassName;
        this.students = students;
    }

    public Classes(String className, List<Student> students) {
        ClassName = className;
        this.students = students;
    }

    public Classes() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
