package com.threads.concurrency.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    @JsonBackReference
    private Chef chef;

    @Column(nullable = false)
    private String name;  // Add the 'name' of dish




    private String status;  // Add status of fuel
}
