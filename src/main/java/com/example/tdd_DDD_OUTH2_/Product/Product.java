package com.example.tdd_DDD_OUTH2_.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    private  Integer id;

    private String Ref;

    private double Quantity;


}
