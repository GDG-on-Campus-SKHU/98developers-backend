package com.example.developers.repository;

import com.example.developers.domain.Explore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExploreRepository extends JpaRepository<Explore, Long> {
    List<Explore> findByName(String name);
    List<Explore> findByTag(String tag);
}
