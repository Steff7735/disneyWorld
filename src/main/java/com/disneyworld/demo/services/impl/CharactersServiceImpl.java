package com.disneyworld.demo.services.impl;

import com.disneyworld.demo.dto.CharactersDto;
import com.disneyworld.demo.dto.CharactersFiltersDto;
import com.disneyworld.demo.entities.Characters;
import com.disneyworld.demo.exceptions.ParamNotFound;
import com.disneyworld.demo.mappers.CharactersMapper;
import com.disneyworld.demo.repositories.CharactersRepository;
import com.disneyworld.demo.repositories.specifications.CharactersSpecification;
import com.disneyworld.demo.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    CharactersMapper charactersMapper;
    @Autowired
    CharactersRepository charactersRepository;

    @Autowired
    CharactersSpecification charactersSpecification;


    public CharactersDto save(CharactersDto charactersDto) {
        Characters entity = charactersMapper.charactersDto2Entity(charactersDto);
        Characters saveEntity = charactersRepository.save(entity);
        CharactersDto result = charactersMapper.characters2Dto(saveEntity,false);
        return result;
    }


    public List<CharactersDto> getAll(){
        List<Characters> entities = charactersRepository.findAll();
        List<CharactersDto> result = charactersMapper.charactersList2DtoList(entities,false);
        return result;
    }


    public CharactersDto getDetails(Long id) {
        Characters characters = this.handleFindById(id);
        CharactersDto resultDto = charactersMapper.characters2Dto(characters,true);
        return resultDto;
    }

    public CharactersDto editById(Long id, CharactersDto charactersToEdit) {
        Characters savedChar = this.handleFindById(id);
        savedChar.setName(charactersToEdit.getName());
        savedChar.setAge(charactersToEdit.getAge());
        savedChar.setWeight(charactersToEdit.getWeight());
        savedChar.setHistory(charactersToEdit.getHistory());
        Characters editedCharacters = charactersRepository.save(savedChar);
        CharactersDto resultDto = charactersMapper.characters2Dto(editedCharacters, false);
        return resultDto;
    }

    public void deleteById(Long id) {
        charactersRepository.deleteById(id);
    }


    public List<CharactersDto> getByFilters(String name, Integer age, List<Long> films) {
        CharactersFiltersDto filtersDto = new CharactersFiltersDto(name, age, films);
        List<Characters> entityList = charactersRepository.findAll(charactersSpecification.getFiltered(filtersDto));
        List<CharactersDto> resultDto = charactersMapper.charactersList2DtoList(entityList, true);
        return resultDto;
    }

    
    public Characters handleFindById(Long id) {
        Optional<Characters> toBeFound = charactersRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Characters for id: " + id);
        }
        return toBeFound.get();
    }
}
