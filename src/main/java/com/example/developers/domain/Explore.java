package com.example.developers.domain;

import com.example.developers.DTO.ExploreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "explore")
@Entity
public class Explore {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "tag", nullable = false, length = 150)
    private String tag;

    @Column(name = "address", nullable = true, length = 150)
    private String address;

    @Column(name = "tele", nullable = true, length = 150)
    private String tele;

    @Column(name = "image", nullable = true, length = 300)
    private String image;

    @Column(name = "latitude", nullable = true)
    private double latitude;

    @Column(name = "longitude", nullable = true)
    private double longitude;


    public ExploreDTO toDTO() {
        return ExploreDTO.builder()
                .id(id)
                .name(name)
                .tag(tag)
                .address(address)
                .tele(tele)
                .image(image)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
