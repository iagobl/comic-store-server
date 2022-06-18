package com.iagobl.server.controller;

import com.iagobl.server.model.Collection;
import com.iagobl.server.services.CollectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-spring/collection")
public class CollectionController {

    @Autowired
    public CollectionServices collectionServices;

    @GetMapping
    public List<HashMap<String, Object>> findAll(){
        List<Collection> collections = collectionServices.findAll();
        List<HashMap<String, Object>> collectionReturnData = new ArrayList<>();

        collections.forEach( collection -> {
            HashMap<String, Object> collectionData = new HashMap<>();
            collectionData.put("id", collection.getId());
            collectionData.put("name", collection.getName());
            collectionData.put("image", collection.getImage());
            collectionData.put("editorial", collection.getEditorial());

            collectionReturnData.add(collectionData);
        });

        return collectionReturnData;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> findById(@PathVariable(value = "id") Long id) {
        Optional<Collection> collection = collectionServices.findById(id);
        HashMap<String, Object> collectionReturnData = new HashMap<>();

        collectionReturnData.put("id", collection.get().getId());
        collectionReturnData.put("name", collection.get().getName());
        collectionReturnData.put("image", collection.get().getImage());
        collectionReturnData.put("editorial", collection.get().getEditorial());

        return collectionReturnData;
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
        System.out.println("hola");
        return collectionServices.collectionImageUpdate(id, imageCollection);
    }

    @PutMapping
    public Collection update(@RequestBody Collection collection) {
        return collectionServices.collectionUpdate(collection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){

        Optional<Collection> collectionDelete = collectionServices.findById(id);
        if(collectionDelete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found"));
        }

        collectionServices.collectionDelete(id);
    }


}
