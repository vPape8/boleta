package com.cordy.bol.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cordy.bol.model.Buque;

@Repository
public interface BuqueRepository extends JpaRepository<Buque, String>{}

