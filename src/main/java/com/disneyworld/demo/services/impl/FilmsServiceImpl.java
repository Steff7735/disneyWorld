package com.disneyworld.demo.services.impl;

import com.disneyworld.demo.dto.FilmsDto;
import com.disneyworld.demo.dto.FilmsFiltersDto;
import com.disneyworld.demo.entities.Characters;
import com.disneyworld.demo.entities.Films;
import com.disneyworld.demo.entities.Genders;
import com.disneyworld.demo.exceptions.ParamNotFound;
import com.disneyworld.demo.mappers.FilmsMapper;
import com.disneyworld.demo.repositories.FilmsRepository;
import com.disneyworld.demo.repositories.specifications.FilmsSpecification;
import com.disneyworld.demo.services.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    FilmsMapper filmsMapper;

    @Autowired
    FilmsRepository filmsRepository;

    @Autowired
    CharactersServiceImpl charactersServiceImpl;

    @Autowired
    GendersServiceImpl gendersServiceImpl;

    @Autowired
    FilmsSpecification filmsSpecification;

  
    public FilmsDto save(FilmsDto filmsDto){
        Films entity = filmsMapper.FilmsDto2Entity(filmsDto);
        Films saveEntity = filmsRepository.save(entity);
        FilmsDto result = filmsMapper.Films2Dto(saveEntity,false);

        return result;
    }

    public void addCharacters(Long filmsId, Long charactersId) {
        Films savedFilms = this.handleFindById(filmsId);
        Characters savedCharacters = charactersServiceImpl.handleFindById(charactersId);
        savedFilms.getCharacters().size();
        savedFilms.addCharacters(savedCharacters);
        filmsRepository.save(savedFilms);
    }

    public void addGenders(Long filmsId, Long gendersId) {
        Films savedFilms = this.handleFindById(filmsId);
        Genders savedGenders = gendersServiceImpl.handleFindById(gendersId);
        savedFilms.getGenders().size();
        savedFilms.addGenders(savedGenders);
        filmsRepository.save(savedFilms);
    }

   
    public List<FilmsDto> getAll(){
        List<Films> entities = filmsRepository.findAll();
        List<FilmsDto> result = filmsMapper.EntityList2DtoList(entities,false);
        return result;
    }


    public FilmsDto getDetails(Long id) {
        Films films = this.handleFindById(id);
        FilmsDto resultDto = filmsMapper.Films2Dto(films, true);
        return resultDto;
    }



    public FilmsDto editById(Long id, FilmsDto filmsToEdit) {
        Films savedFilms = this.handleFindById(id);
        savedFilms.setImage(filmsToEdit.getImage());
        savedFilms.setTitle(filmsToEdit.getTitle());
        savedFilms.setStars(filmsToEdit.getStars());
        savedFilms.setDateCreation(filmsMapper.String2LocalDate(filmsToEdit.getDateCreation()));
        Films editedFilms = filmsRepository.save(savedFilms);
        FilmsDto resultDto = filmsMapper.Films2Dto(editedFilms, false);
        return resultDto;
    }

    
    public void deleteById(Long id) {

        filmsRepository.deleteById(id);
    }

    

    public List<FilmsDto> getByFilters(String title, List<Long> genders, String order) {
        FilmsFiltersDto filmsFilters = new FilmsFiltersDto(title, genders, order);
        List<Films> entityList = filmsRepository.findAll(filmsSpecification.getFiltered(filmsFilters));
        List<FilmsDto> resultDto = filmsMapper.EntityList2DtoList(entityList, true);
        return resultDto;
    }


    public Films handleFindById(Long id) {
        Optional<Films> toBeFound = filmsRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Films for id: " + id);
        }
        return toBeFound.get();
    }
}
