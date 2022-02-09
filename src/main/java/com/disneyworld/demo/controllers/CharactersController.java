package com.disneyworld.demo.controllers;

import com.disneyworld.demo.dto.CharactersDto;
import com.disneyworld.demo.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("characters")
@RestController
public class CharactersController {

    @Autowired
    CharactersService charactersService;


    @PostMapping
    public ResponseEntity<CharactersDto> save(@RequestBody CharactersDto characters){
        CharactersDto charactersSave = charactersService.save(characters);
        return ResponseEntity.status(HttpStatus.CREATED).body(charactersSave);
    }
    @GetMapping("/All")
    public ResponseEntity<List<CharactersDto>> getAll() {
        List<CharactersDto> charactersDto = charactersService.getAll();
        return ResponseEntity.ok().body(charactersDto);
    }
    
     @GetMapping("/details/{id}")
    public ResponseEntity<CharactersDto> getDetailsById(@PathVariable Long id) {
        CharactersDto charactersDetails = charactersService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(charactersDetails);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CharactersDto> edit(@PathVariable Long id, @RequestBody CharactersDto charactersToEdit) {
        CharactersDto editedCharacters = charactersService.editById(id, charactersToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedCharacters);
    }

   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        charactersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<List<CharactersDto>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> films
    ) {
        List<CharactersDto> charactersList = charactersService.getByFilters(name, age, films);
        return ResponseEntity.status(HttpStatus.OK).body(charactersList);


    }
}
