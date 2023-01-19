package com.example.dbconnection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    long id;
    String name;
    String category;
    String Description;


}
