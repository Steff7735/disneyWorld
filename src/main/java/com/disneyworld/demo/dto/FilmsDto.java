package com.disneyworld.demo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class FilmsDto {

    private String id;

    private String image;

    private String title;

    private String dateCreation;

    private Integer stars;

    private List<CharactersDto> charactersDto ;

    private List<GendersDto> gendersDto;
}
