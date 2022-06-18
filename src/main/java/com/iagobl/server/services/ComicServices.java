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
        Comic update = comicRepository.findById(id).orElse(null);

        try {
            update.setImage(imageComic.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return update;
    }

    @Transactional
    public Comic comicUpdate(Comic comic){
        Comic update = comicRepository.findById(comic.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));
        Collection collection = collectionServices.findById(update.getCollection().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found")));
        Optional<Comic> comicSearch = comicRepository.findByName(comic.getName());
        if(comicSearch.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND, String.format("Comic repeat name"));
        }

        try{
            if(!comic.getName().isEmpty() && !comic.getName().isBlank()){
                update.setName(comic.getName());
            }

            if(!comic.getSynopsis().isEmpty() && !comic.getSynopsis().isBlank()){
                update.setSynopsis(comic.getSynopsis());
            }

            if(comic.getNumber() >= 0){
                update.setNumber(comic.getNumber());
            }

            if(comic.getPage() >= 0) {
                update.setPage(comic.getPage());
            }

            if(!comic.getTapa().isEmpty() && !comic.getTapa().isBlank()){
                update.setTapa(comic.getTapa());
            }

            if(comic.getAnhoPublication() >= 0){
                update.setAnhoPublication(comic.getAnhoPublication());
            }

            if(comic.getDataAcquisition() != null){
                update.setDataAcquisition(comic.getDataAcquisition());
            }

            if(!comic.getState().isEmpty() && !comic.getState().isBlank()) {
                update.setState(comic.getState());
            }

            if(comic.getPrice() >= 0.0){
                update.setPrice(comic.getPrice());
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
