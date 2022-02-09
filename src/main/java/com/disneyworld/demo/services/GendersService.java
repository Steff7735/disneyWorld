package com.disneyworld.demo.services;

import com.disneyworld.demo.dto.GendersDto;

import java.util.List;

public interface GendersService {

    List<GendersDto> getAll();

    GendersDto save(GendersDto gendersDto);

    public void deletedById(Long id);

    public GendersDto editById(Long id, GendersDto gendersToEdit);

}
