package com.disneyworld.demo.services.impl;

import com.disneyworld.demo.dto.GendersDto;
import com.disneyworld.demo.entities.Genders;
import com.disneyworld.demo.exceptions.ParamNotFound;
import com.disneyworld.demo.mappers.GendersMapper;
import com.disneyworld.demo.repositories.GendersRepository;
import com.disneyworld.demo.services.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GendersServiceImpl implements GendersService {

    @Autowired
    GendersMapper gendersMapper;

    @Autowired
    GendersRepository gendersRepository;


    public GendersDto save(GendersDto gendersDto) {
        Genders entity = gendersMapper.gendersDto2Entity(gendersDto);
        Genders savedEntity = gendersRepository.save(entity);
        GendersDto result = gendersMapper.genders2Dto(savedEntity, false);
        return result;
    }


    public List<GendersDto> getAll() {
        List<Genders> entities = gendersRepository.findAll();
        List<GendersDto> result = this.gendersMapper.gendersList2DtoList(entities, false);
        return result;
    }


    public void deletedById(Long id) {
        gendersRepository.deleteById(id);
    }


    public GendersDto editById(Long id, GendersDto gendersToEdit) {
        Genders savedGenders = this.handleFindById(id);
        savedGenders.setImage(gendersToEdit.getImage());
        savedGenders.setName(gendersToEdit.getName());
        Genders editedGenders = gendersRepository.save(savedGenders);
        GendersDto resultDto = gendersMapper.genders2Dto(editedGenders, false);
        return resultDto;
    }

    public Genders handleFindById(Long id) {
        Optional<Genders> toBeFound = gendersRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Genders for id: " + id);
        }
        return toBeFound.get();
    }
}
