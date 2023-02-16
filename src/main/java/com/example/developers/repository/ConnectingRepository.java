package com.example.developers.repository;

import com.example.developers.DTO.ConnectingDTO;
import com.example.developers.domain.Connecting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectingRepository extends JpaRepository<Connecting, Long> {
    ConnectingDTO findByStore(String store);
}
