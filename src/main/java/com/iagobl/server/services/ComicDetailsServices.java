package com.iagobl.server.services;

import com.iagobl.server.model.Comic;
import com.iagobl.server.model.ComicDetails;
import com.iagobl.server.repository.ComicDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComicDetailsServices {

    @Autowired
    private ComicDetailsRepository comicDetailsRepository;

    public List<ComicDetails> findAll(){
        return comicDetailsRepository.findAll();
    }

    public Optional<ComicDetails> findById(Long id){
        return comicDetailsRepository.findById(id);
    }

    public ComicDetails save(ComicDetails comic) {
        return comicDetailsRepository.save(comic);
    }

    @Transactional
    public ComicDetails update(Long id, LocalDate dateAcquistion, String state, double price){

        ComicDetails update = comicDetailsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        if(dateAcquistion != null){
            update.setDateAcquistion(dateAcquistion);
        }

        if(!state.isEmpty() && !state.isBlank()){
            update.setState(state);
        }

        if(price >= 0){
            update.setPrice(price);
        }

        return update;
    }

    @Transactional
    public void delete(Long id) {
        ComicDetails delete = comicDetailsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        comicDetailsRepository.deleteById(delete.getId());
    }
}
