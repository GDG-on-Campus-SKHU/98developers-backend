package com.example.developers.DTO;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExploreDTO {
    private Long id;

    private String name;

    private String tag;

    private String address;

    private String tele;

    private String image;

    private double latitude;

    private double longitude;

}
