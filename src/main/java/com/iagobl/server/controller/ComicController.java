package com.iagobl.server.controller;

import com.iagobl.server.model.Comic;
import com.iagobl.server.services.ComicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-spring/comic")
public class ComicController {

    @Autowired
    public ComicServices comicServices;

    @GetMapping
    public List<Comic> findAll() {
        return comicServices.findAll();
    }

    @GetMapping("/findByName/{name}")
    public Comic findName(@PathVariable(value = "name") String name){
        Optional<Comic> comic = comicServices.findByName(name);
        if (!comic.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found"));
        }

        return comic.get();
    }

    @PostMapping
    public Comic save(@RequestBody Comic comic){
        return comicServices.comicSave(comic);
    }

    @PutMapping("/image/{id}")
    public Comic updatePhoto(@PathVariable(value = "id") Long id, @RequestParam(value = "image") MultipartFile imageComic){
        return comicServices.comicImageUpdate(id, imageComic);
    }

    @PutMapping("/{id}")
    public Comic update(@PathVariable(value = "id") Long id,
                        @RequestParam(required = true, value = "name") String name,
                        @RequestParam(required = true, value = "synopsis") String synopsis,
                        @RequestParam(required = true, value = "number") Integer number,
                        @RequestParam(required = true, value = "collectionId") Long idCollection){
        return comicServices.comicUpdate(id, name, synopsis, number, idCollection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        comicServices.comicDelete(id);
    }


}
