package com.iagobl.server.controller;

import com.iagobl.server.model.Author;
import com.iagobl.server.model.Comic;
import com.iagobl.server.services.AuthorServices;
import com.iagobl.server.services.ComicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api-spring/comic")
public class ComicController {

    @Autowired
    public ComicServices comicServices;
    @Autowired
    public AuthorServices authorServices;

    @GetMapping
    public List<Comic> findAll() {
        return comicServices.findAll();
    }

    @GetMapping("/findComicReport/{name}")
    public HashMap<String, Object> findComicReport(@PathVariable(value = "name") String name){
        String newName = name.replaceAll(Pattern.quote("+"), " ");
        Optional<Comic> comic = comicServices.findByName(newName);
        HashMap<String, Object> comicReturnData = new HashMap<>();

        comicReturnData.put("id", comic.get().getId());
        comicReturnData.put("name", comic.get().getName());
        comicReturnData.put("image", comic.get().getImage());
        comicReturnData.put("synopsis", comic.get().getSynopsis());
        comicReturnData.put("number", comic.get().getNumber());
        comicReturnData.put("page", comic.get().getPage());
        comicReturnData.put("tapa", comic.get().getTapa());
        comicReturnData.put("anhoPublication", comic.get().getAnhoPublication());
        comicReturnData.put("dataAcquisition", comic.get().getDataAcquisition());
        comicReturnData.put("state", comic.get().getState());
        comicReturnData.put("price", comic.get().getPrice());
        comicReturnData.put("timeDedicated", comic.get().getAuthorComic().getTimeDedicated());
        comicReturnData.put("collection_id", comic.get().getCollection().getId());
        comicReturnData.put("authorName", comic.get().getAuthorComic().getAuthor().getName());

        return comicReturnData;
    }

    @GetMapping("/findByName/{name}")
    public Comic findName(@PathVariable(value = "name") String name){
        Optional<Comic> comic = comicServices.findByName(name);
        if (!comic.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found"));
        }

        return comic.get();
    }

    @GetMapping("/{id}")
    public Comic findById(@PathVariable(value = "id") Long id){
        Optional<Comic> comic = comicServices.findById(id);
        return comic.get();
    }

    @PostMapping("/{id}")
    public Comic save(@RequestBody Comic comic, @PathVariable Long id){
        comic.getAuthorComic().setComic(comic);
        comic.getAuthorComic().setAuthor(authorServices.findById(id).get());
        return comicServices.comicSave(comic);
    }

    @PutMapping("/image/{id}")
    public Comic updatePhoto(@PathVariable(value = "id") Long id, @RequestParam(value = "imageComic") MultipartFile imageComic){
        return comicServices.comicImageUpdate(id, imageComic);
    }

    @PutMapping
    public Comic update(@RequestBody Comic comic){
        return comicServices.comicUpdate(comic);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        comicServices.comicDelete(id);
    }


}
