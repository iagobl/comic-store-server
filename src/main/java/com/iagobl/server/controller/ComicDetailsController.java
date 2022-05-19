package com.iagobl.server.controller;

import com.iagobl.server.model.ComicDetails;
import com.iagobl.server.services.ComicDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-spring/comicdetails")
public class ComicDetailsController {

    @Autowired
    public ComicDetailsServices comicDetailsServices;

    @GetMapping
    public List<ComicDetails> findAll() {
        return comicDetailsServices.findAll();
    }

    @GetMapping("/{id}")
    public ComicDetails findById(@PathVariable(value = "id") Long id){

        Optional<ComicDetails> comicDetailsFind = comicDetailsServices.findById(id);
        if(!comicDetailsFind.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found"));
        }

        return comicDetailsFind.get();
    }

    @PostMapping
    public ComicDetails save(@RequestBody ComicDetails comicDetails){
        return comicDetailsServices.save(comicDetails);
    }

    @PutMapping("/{id}")
    public ComicDetails update(@PathVariable(value = "id") Long id,
                               @RequestParam(required = true, value = "dateAcquistion") LocalDate date,
                               @RequestParam(required = true, value = "state") String state,
                               @RequestParam(required = true, value = "price") double price){
        return comicDetailsServices.update(id, date, state, price);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        comicDetailsServices.delete(id);
    }
}
