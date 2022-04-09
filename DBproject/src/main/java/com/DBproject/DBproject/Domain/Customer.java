package com.DBproject.DBproject.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    private String customer_name;
    private String customer_number;

}
