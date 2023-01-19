package com.example.dbconnection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Auction {
    @Id
    long id;
    Date startingDate;
    Date endingDate;
    List<Product> products;

}
