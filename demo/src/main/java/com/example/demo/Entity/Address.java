package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
@Table(name = "addresses")
@Entity
public class Address {
    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator ="address_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(name="street")
    private String street;
    @Column(name="city")
    private String city;
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;



    public Address() {
    }

    public Address(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Address( String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
