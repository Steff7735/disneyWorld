package com.disneyworld.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GendersDto {

    private Long id;

    private String image;

    private String name;

    private boolean deleted;

    private List<FilmsDto> filmsDto;
}
