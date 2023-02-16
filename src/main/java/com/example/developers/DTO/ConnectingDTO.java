package com.example.developers.DTO;

import lombok.*;


@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectingDTO {
    private Long id;

    private String store;

    private String tag;

    private String location;

    private String phone_number;

    private String homepage;

    private String image;
}
