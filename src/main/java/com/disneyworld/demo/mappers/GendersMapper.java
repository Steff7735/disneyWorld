package com.disneyworld.demo.mappers;

import com.disneyworld.demo.dto.GendersDto;
import com.disneyworld.demo.entities.Genders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GendersMapper {
    @Lazy
    @Autowired
    FilmsMapper filmsMapper;

    public Genders gendersDto2Entity(GendersDto dto) {
        Genders entity = new Genders();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        return entity;
    }

    public GendersDto genders2Dto(Genders entity, boolean b) {
        GendersDto dto = new GendersDto();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        if (b) {
            dto.setFilmsDto(filmsMapper.EntityList2DtoList(entity.getFilmsGenders(), false));
        }
        return dto;

    }

    public List<GendersDto> gendersList2DtoList(List<Genders> gendersList, boolean b) {
        List<GendersDto> dto = new ArrayList<>();
        for (Genders entity : gendersList) {
            dto.add(this.genders2Dto(entity, false));
        }
        return dto;
    }

}
