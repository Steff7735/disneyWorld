package com.disneyworld.demo.controllers;

import com.disneyworld.demo.dto.GendersDto;
import com.disneyworld.demo.services.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("genders")
public class GendersController {

    @Autowired
    GendersService gendersService;

    @PostMapping
    public ResponseEntity<GendersDto> save(@RequestBody GendersDto genders){
        GendersDto gendersSave = gendersService.save(genders);
        return ResponseEntity.status(HttpStatus.CREATED).body(gendersSave);
    }
    @GetMapping("/all")
    public ResponseEntity<List<GendersDto>> getAll(){
        List<GendersDto> genders = gendersService.getAll();
        return ResponseEntity.ok().body(genders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GendersDto> edit(@PathVariable Long id, @RequestBody GendersDto gendersToEdit){
        GendersDto editedGenders = gendersService.editById(id, gendersToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenders);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        gendersService.deletedById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
