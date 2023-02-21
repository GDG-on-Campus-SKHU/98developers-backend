package com.example.developers.domain;

import com.example.developers.DTO.ConnectingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "connecting")
@Entity
public class Connecting {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "store", nullable = false, length = 150)
    private String store;

    @Column(name = "tag", nullable = false, length = 150)
    private String tag;

    @Column(name = "location", nullable = true, length = 150)
    private String location;

    @Column(name = "phone_number", nullable = true, length = 150)
    private String phone_number;

    @Column(name = "homepage", nullable = true, length = 150)
    private String homepage;

    @Column(name = "image", nullable = true, length = 300)
    private String image;

    public ConnectingDTO toDTO() {
        return ConnectingDTO.builder()
                .store(store)
                .tag(tag)
                .location(location)
                .phone_number(phone_number)
                .homepage(homepage)
                .image(image)
                .build();
    }
}
