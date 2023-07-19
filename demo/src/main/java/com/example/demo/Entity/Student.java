package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Transactional
@NoArgsConstructor
@Data
@Entity
@Table(name = "students",
    uniqueConstraints = {@UniqueConstraint(name = "student_email_unique",columnNames = "email")})
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator ="student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "ten",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(name = "age")
    @Transient
    private Integer age;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

     public Student(String name, LocalDate dob, String email, Address address) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
    }


    public Student(Long id,
                   String name,
                   LocalDate dob,
                   String email,
                   Address address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;

    }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return Period.between(this.dob,LocalDate.now()).getYears()+1;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public LocalDate getDob() {
            return dob;
        }

        public void setDob(LocalDate dob) {
            this.dob = dob;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public Address getAddress() {
        return address;
    }

        public void setAddress(Address address) {
        this.address = address;
    }


}
