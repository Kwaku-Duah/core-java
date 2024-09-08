package com.threads.concurrency.Services;

import com.threads.concurrency.Entities.*;
import com.threads.concurrency.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    // Method to save a new chef
    public Chef saveChef(Chef chef) {
        return chefRepository.save(chef);
    }

    // Method to retrieve all chefs
    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
    }

    // Method to retrieve a chef by ID
    public Chef getChefById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    // Method to delete a chef by ID
    public void deleteChefById(Long id) {
        chefRepository.deleteById(id);
    }
}
