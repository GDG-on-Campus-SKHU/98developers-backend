package com.example.developers.repository;

import com.example.developers.domain.Connecting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectingRepository extends JpaRepository<Connecting, Long> {
    List<Connecting> findByStore(String store);
    List<Connecting> findByTag(String tag);
}
