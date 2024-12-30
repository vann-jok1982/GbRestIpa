package com.example.Web_Koleso.repositories;

import com.example.Web_Koleso.models.Tire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TireRepository extends JpaRepository<Tire, Long> {
    public Optional<Tire> findByArticle(Long article);
}
