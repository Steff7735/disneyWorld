package com.disneyworld.demo.controllers;

import com.disneyworld.demo.dto.FilmsDto;
import com.disneyworld.demo.services.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
public class FilmsController {

    @Autowired
    FilmsService filmsService;


    @PostMapping
    public ResponseEntity<FilmsDto> save(@RequestBody FilmsDto films){
        FilmsDto filmsSave = filmsService.save(films);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmsSave);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FilmsDto>> getAll(){
        List<FilmsDto> moviesList = filmsService.getAll();
        return ResponseEntity.ok().body(moviesList);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<FilmsDto> getById(@PathVariable Long id){
        FilmsDto films = filmsService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(films);
    }

    @PostMapping("/{filmsId}/characters/{charactersId}")
    public ResponseEntity<Void> addCharacters(@PathVariable Long filmsId, @PathVariable Long charactersId){
        filmsService.addCharacters(filmsId, charactersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{filmsId}/genders/{gendersId}")
    public ResponseEntity<Void> addGenders(@PathVariable Long filmsId, @PathVariable Long gendersId){
        filmsService.addGenders(filmsId, gendersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmsDto> edit(@PathVariable Long id, @RequestBody FilmsDto filmsToEdit){
        FilmsDto editedFilms = filmsService.editById(id, filmsToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedFilms);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        filmsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<FilmsDto>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<Long> genders,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<FilmsDto> filteredFilms = filmsService.getByFilters(title, genders, order);
        return ResponseEntity.status(HttpStatus.OK).body(filteredFilms);
    }
}
