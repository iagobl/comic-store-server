package com.iagobl.server.controller;

import com.iagobl.server.model.AuthorComic;
import com.iagobl.server.services.AuthorComicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api-spring/authorcomic")
public class AuthorComicController {

    @Autowired
    private AuthorComicServices authorComicServices;

    @GetMapping
    public List<AuthorComic> findAll(){
        return authorComicServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AuthorComic> findById(@PathVariable(value = "id") Long id){ return authorComicServices.findById(id); }

    @GetMapping("/findByIdComic/{id}")
    public Optional<AuthorComic> findByIdComic(@PathVariable(value = "id") Long id) { return authorComicServices.findByIdComic(id); }

    @PostMapping
    public AuthorComic save(@RequestBody AuthorComic authorComic){
        return authorComicServices.save(authorComic);
    }

    @PutMapping("/{id}")
    public AuthorComic update(@PathVariable(value = "id") Long id, @RequestParam(required = true, value = "timeDedicated") int timeDedicated){
        return authorComicServices.update(id, timeDedicated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        Optional<AuthorComic> delete = authorComicServices.findById(id);
        if(!delete.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Relation not found"));
        }

        authorComicServices.delete(id);
    }

}
