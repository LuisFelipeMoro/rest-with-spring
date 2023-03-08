package com.rest.demo.model;

import com.mysql.cj.result.AbstractDateTimeValueFactory;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "author", nullable = false, length = 255)
    private String author;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "title", nullable = false, length = 255)
    private String title;
}
