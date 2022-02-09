package com.disneyworld.demo.services;

import com.disneyworld.demo.dto.FilmsDto;

import java.util.List;

public interface FilmsService {

    FilmsDto save(FilmsDto filmsDto);

    List<FilmsDto> getAll();

    void deleteById(Long id);

    FilmsDto getDetails(Long id);

    FilmsDto editById(Long id, FilmsDto filmsToEdit);

    List<FilmsDto> getByFilters(String title, List<Long> genders, String order);

    void addCharacters(Long filmsId, Long charactersId);

    void addGenders(Long filmsId, Long gendersId);

}
