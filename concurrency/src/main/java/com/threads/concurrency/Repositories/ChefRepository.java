package com.threads.concurrency.Repositories;



import com.threads.concurrency.Entities.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {
    // Additional query methods can be defined here if needed
}
