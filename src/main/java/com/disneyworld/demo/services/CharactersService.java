package com.disneyworld.demo.services;

import com.disneyworld.demo.dto.CharactersDto;

import java.util.List;

public interface CharactersService {

    List<CharactersDto> getAll();

    CharactersDto save(CharactersDto charactersDto);

    CharactersDto getDetails(Long id);

    CharactersDto editById(Long id, CharactersDto charactersToEdit);

    List<CharactersDto> getByFilters(String name, Integer age, List<Long> films);

    void deleteById(Long id);
}
