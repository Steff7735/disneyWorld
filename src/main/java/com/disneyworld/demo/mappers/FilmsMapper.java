package com.disneyworld.demo.mappers;

import com.disneyworld.demo.dto.FilmsBasicDto;
import com.disneyworld.demo.dto.FilmsDto;
import com.disneyworld.demo.entities.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsMapper {

    @Autowired
    CharactersMapper charactersMapper;
    @Lazy
    @Autowired
    GendersMapper gendersMapper;

    public Films FilmsDto2Entity(FilmsDto dto) {
        Films entity = new Films();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setStars(dto.getStars());
        entity.setDateCreation(this.String2LocalDate(dto.getDateCreation()));
        return entity;
    }

    public FilmsDto Films2Dto(Films entity, boolean b) {
        FilmsDto dto = new FilmsDto();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setStars(entity.getStars());
        dto.setDateCreation(this.LocalDateToString(entity.getDateCreation()));
        if (b) {
            dto.setCharactersDto(this.charactersMapper.charactersList2DtoList(entity.getCharacters(), false));
            dto.setGendersDto(this.gendersMapper.gendersList2DtoList(entity.getGenders(), false));
        }
        return dto;
    }

    public List<FilmsDto> EntityList2DtoList(List<Films> filmsList, boolean b) {
        List<FilmsDto> dto = new ArrayList<>();
        for (Films entity : filmsList) {
            dto.add(this.Films2Dto(entity, b));
        }
        return dto;
    }

    public FilmsBasicDto films2BasicDto(Films entity) {
        FilmsBasicDto dto = new FilmsBasicDto();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setDateCreation(this.LocalDateToString(entity.getDateCreation()));
        return dto;
    }

    public List<FilmsBasicDto> FilmsList2BasicDtoList(List<Films> entities) {
        List<FilmsBasicDto> dto = new ArrayList<>();
        for (Films ent : entities) {
            dto.add(this.films2BasicDto(ent));
        }
        return dto;
    }

    public LocalDate String2LocalDate(String enteredDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
        return transformedDate;
    }

    public String LocalDateToString(LocalDate creationDate) {
        String formattedDate = creationDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return formattedDate;
    }
}
