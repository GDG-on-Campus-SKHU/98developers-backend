package com.example.developers.controller;

import com.example.developers.DTO.ConnectingDTO;
import com.example.developers.service.ConnectingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConnectingController {

    private final ConnectingService connectingService;

    @GetMapping("/connecting")
    public ResponseEntity<List<ConnectingDTO>> findAllConnecting (
    ) {
        List<ConnectingDTO> connectingDTO = connectingService.findAllConnecting();

        return ResponseEntity
                .ok(connectingDTO);
    }
}
