package com.iagobl.server.controller;

import com.iagobl.server.model.Comic;
import com.iagobl.server.services.ComicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-spring/comic")
public class ComicController {

    @Autowired
    public ComicServices comicServices;

    @GetMapping
    public List<Comic> findAll(){
        return comicServices.findAll();
    }

    @PostMapping
    public Comic save(@RequestBody Comic comic){
        return comicServices.comicSave(comic);
    }

    @PutMapping("/{id}")
    public Comic update(@PathVariable(value = "id") Long id,
                        @RequestParam(required = true, value = "name") String name,
                        @RequestParam(required = true, value = "synopsis") String synopsis,
                        @RequestParam(required = true, value = "number") Integer number){
        return comicServices.comicUpdate(id, name, synopsis, number);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        comicServices.comicDelete(id);
    }


}
