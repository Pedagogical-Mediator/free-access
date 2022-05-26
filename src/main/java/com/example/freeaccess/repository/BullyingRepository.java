package com.example.freeaccess.repository;


import com.example.freeaccess.domain.bullying.Bullying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BullyingRepository extends JpaRepository<Bullying, Integer> {
}
