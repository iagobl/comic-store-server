package com.iagobl.server.services;

import com.iagobl.server.model.Collection;
import com.iagobl.server.model.Comic;
import org.springframework.http.HttpStatus;
import com.iagobl.server.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComicServices {

    @Autowired
    private ComicRepository comicRepository;
    @Autowired
    private CollectionServices collectionServices;

    public List<Comic> findAll() {
        return comicRepository.findAll();
    }

    public Optional<Comic> findByName(String name) {
        return comicRepository.findByName(name);
    }

    public Optional<Comic> findById(Long id){
        return comicRepository.findById(id);
    }

    public Comic comicSave(Comic comic){
        Optional<Comic> search = findByName(comic.getName());
        if(search.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND, String.format("Comic found"));
        }

        return comicRepository.save(comic);
    }

    @Transactional
    public Comic comicImageUpdate(Long id, MultipartFile imageComic){
        Comic update = comicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        try {
            update.setImage(imageComic.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return update;
    }

    @Transactional
    public Comic comicUpdate(Long id, String name, String synopsis, Integer number, Integer page, String tapa, Integer anhoPublication, LocalDate dateAcquistion, String state, Double price){


        Comic update = comicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));
        Collection collection = collectionServices.findById(update.getCollection().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found")));

        try{
            if(!name.isEmpty() && !name.isBlank()){
                update.setName(name);
            }

            if(!synopsis.isEmpty() && !synopsis.isBlank()){
                update.setSynopsis(synopsis);
            }

            if(number != null){
                update.setNumber(number);
            }

            if(page != null) {
                update.setPage(page);
            }

            if(!tapa.isEmpty() && !tapa.isBlank()){
                update.setTapa(tapa);
            }

            if(anhoPublication != null){
                update.setAnhoPublication(anhoPublication);
            }

            //if(dateAcquistion != null){
                update.setDateAcquistion(dateAcquistion);
            //}

            if(!state.isEmpty() && !state.isBlank()) {
                update.setState(state);
            }

            if(price != null){
                update.setPrice(price);
            }

            collection.getComicList().add(update);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return update;
    }

    @Transactional
    public void comicDelete(Long id){

        Comic delete = comicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        comicRepository.deleteById(delete.getId());
    }
}
