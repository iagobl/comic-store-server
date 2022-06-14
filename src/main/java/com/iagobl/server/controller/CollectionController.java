package com.iagobl.server.controller;

import com.iagobl.server.model.Collection;
import com.iagobl.server.services.CollectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-spring/collection")
public class CollectionController {

    @Autowired
    public CollectionServices collectionServices;

    @GetMapping
    public List<Collection> findAll(){
        return collectionServices.findAll();
    }

    @GetMapping("/findByName/{name}")
    public Collection findName(@PathVariable(value = "name") String name){
        Optional<Collection> collection = collectionServices.findByName(name);
        if(!collection.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found"));
        }

        return collection.get();
    }

    @PostMapping
    public Collection save(@RequestBody Collection collection){
        return collectionServices.collectionSave(collection);
    }

    @PutMapping("/image/{id}")
    public Collection updatePhoto(@PathVariable(value = "id") Long id, @RequestParam(value = "imageCollection")MultipartFile imageCollection){
        return collectionServices.collectionImageUpdate(id, imageCollection);
    }

    @PutMapping("/{id}")
    public Collection update(@PathVariable(value = "id") Long id,
                             @RequestParam(required = true, value = "name") String name,
                             @RequestParam(required = true, value = "editorial") String editorial){
        return collectionServices.collectionUpdate(id, name, editorial);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){

        Optional<Collection> collectionDelete = collectionServices.findById(id);
        if(!collectionDelete.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found"));
        }

        collectionServices.collectionDelete(id);
    }


}
