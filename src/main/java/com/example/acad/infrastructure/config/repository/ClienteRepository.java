package com.example.acad.infrastructure.config.repository;


import com.example.acad.domain.entity.exception.repository.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,String> {
}
