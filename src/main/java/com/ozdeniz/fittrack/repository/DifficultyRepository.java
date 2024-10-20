package com.ozdeniz.fittrack.repository;

import com.ozdeniz.fittrack.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {



}
