package com.adrian.rosasal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrian.rosasal.model.Citas;
@Repository
public interface CitasRepository extends JpaRepository<Citas, Integer> {

}