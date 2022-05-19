package com.iagobl.server.services;

import com.iagobl.server.model.Comic;
import org.springframework.http.HttpStatus;
import com.iagobl.server.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ComicServices {

    @Autowired
    private ComicRepository comicRepository;

    public List<Comic> findAll() {
        return comicRepository.findAll();
    }

    public Optional<Comic> findById(Long id){
        return comicRepository.findById(id);
    }

    public Comic comicSave(Comic comic){
        return comicRepository.save(comic);
    }

    @Transactional
    public Comic comicUpdate(Long id, String name, String synopsis, Integer number){

        Comic update = comicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        if(!name.isEmpty() && !name.isBlank()){
            update.setName(name);
        }

        if(!synopsis.isEmpty() && !synopsis.isBlank()){
            update.setSynopsis(synopsis);
        }

        if(number >= 0 && number != null){
            update.setNumber(number);
        }

        return update;
    }

    @Transactional
    public void comicDelete(Long id){

        Comic delete = comicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Comic not found")));

        comicRepository.deleteById(delete.getId());
    }
}
