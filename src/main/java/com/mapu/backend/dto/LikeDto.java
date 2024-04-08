package com.mapu.backend.dto;

import lombok.Data;

@Data
public class LikeDto {

    private Long id;

    private TwitDto twit;

    private UserDto user;

}
