package com.iagobl.server.controller;

import com.iagobl.server.model.Author;
import com.iagobl.server.services.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-spring/author")
public class AuthorController {

    @Autowired
    private AuthorServices AuthorServices;

    @GetMapping
    public List<Author> findAll() { return AuthorServices.findAll(); }

    @GetMapping("/findByName/{name}")
    public Author findName(@PathVariable(value="name") String name) {

        Optional<Author> author = AuthorServices.findByName(name);
        if(!author.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author not found"));
        }

        return author.get();
    }

    @PostMapping
    public Author save(@RequestBody Author author){
        return AuthorServices.authorSave(author);
    }

    @PutMapping("/image/{id}")
    public Author updatePhoto(@PathVariable(value = "id") Long id, @RequestParam(value = "imageAuthor") MultipartFile imageAuthor) {
        return AuthorServices.authorImageUpdate(id, imageAuthor);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable(value = "id") Long id, @RequestParam(required = true, value = "name") String name, @RequestParam(required = true, value = "surname") String surname){
        return AuthorServices.authorUpdate(id, name, surname);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {

        Optional<Author> authorDelete = AuthorServices.findById(id);
        if (!authorDelete.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author not found"));
        }

        AuthorServices.authorDelete(id);
    }
}
