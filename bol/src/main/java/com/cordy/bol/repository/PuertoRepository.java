package com.cordy.bol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cordy.bol.model.Puerto;

@Repository
public interface PuertoRepository extends JpaRepository<Puerto, Integer> {}
