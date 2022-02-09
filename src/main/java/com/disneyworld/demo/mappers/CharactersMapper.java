package com.disneyworld.demo.mappers;

import com.disneyworld.demo.dto.CharactersBasicDto;
import com.disneyworld.demo.dto.CharactersDto;
import com.disneyworld.demo.entities.Characters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharactersMapper {

    @Lazy
    @Autowired
    FilmsMapper filmsMapper;

    public Characters charactersDto2Entity(CharactersDto dto) {
        Characters entity = new Characters();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        return entity;
    }

    public CharactersDto characters2Dto(Characters entity, boolean b) {
        CharactersDto dto = new CharactersDto();
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (b) {
            dto.setFilmsDto(filmsMapper.EntityList2DtoList(entity.getFilmsCharacters(), false));
        }
        return dto;
    }

    public List<CharactersDto> charactersList2DtoList(List<Characters> entities, boolean b) {
        List<CharactersDto> dto = new ArrayList<>();
        for (Characters entity : entities) {
            dto.add(this.characters2Dto(entity, b));
        }
        return dto;
    }

    public CharactersBasicDto characters2BasicDto(Characters entity) {
        CharactersBasicDto dto = new CharactersBasicDto();
        dto.setAge(entity.getAge());
        dto.setName(entity.getName());
        return dto;
    }

    public List<CharactersBasicDto> charactersList2BasicDtoList(List<Characters> entities) {
        List<CharactersBasicDto> dto = new ArrayList<>();
        for (Characters ent : entities) {
            dto.add(this.characters2BasicDto(ent));
        }
        return dto;
    }
}
