package com.example.developers.controller;

import com.example.developers.DTO.ExploreDTO;
import com.example.developers.service.ExploreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping("/explore")
    public ResponseEntity<List<ExploreDTO>> findAllConnecting (
    ) {
        List<ExploreDTO> exploreDTO = exploreService.findAllConnecting();

        return ResponseEntity
                .ok(exploreDTO);
    }
}
