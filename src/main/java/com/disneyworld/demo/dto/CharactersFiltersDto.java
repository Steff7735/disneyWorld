package com.disneyworld.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharactersFiltersDto {

    private String name;
    private Integer age;
    private List<Long> films;
}
