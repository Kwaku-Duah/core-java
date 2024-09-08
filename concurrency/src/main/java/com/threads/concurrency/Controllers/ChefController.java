package com.threads.concurrency.Controllers;



import com.threads.concurrency.Entities.*;
import com.threads.concurrency.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;

    
    /** 
     * @param chef
     * @return ResponseEntity<String>
     */
    // Endpoint to create a new chef
    @PostMapping
    public ResponseEntity<String> createChef(@RequestBody Chef chef) {
        Chef savedChef = chefService.saveChef(chef);
        return new ResponseEntity<>("Chef created with ID: " + savedChef.getId() + " - Multithreading done", HttpStatus.CREATED);
    }

    // Endpoint to get all chefs
    @GetMapping
    public ResponseEntity<List<Chef>> getAllChefs() {
        List<Chef> chefs = chefService.getAllChefs();
        return new ResponseEntity<>(chefs, HttpStatus.OK);
    }

    // Endpoint to get a chef by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getChefById(@PathVariable Long id) {
        Chef chef = chefService.getChefById(id);
        if (chef != null) {
            return new ResponseEntity<>("Chef found: " + chef.getName() + " - Multithreading done", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Chef not found - Multithreading done", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a chef by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChef(@PathVariable Long id) {
        chefService.deleteChefById(id);
        return new ResponseEntity<>("Chef deleted - Multithreading done", HttpStatus.OK);
    }
}
