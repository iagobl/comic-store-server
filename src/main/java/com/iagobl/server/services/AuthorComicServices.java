package com.iagobl.server.services;

import com.iagobl.server.model.AuthorComic;
import com.iagobl.server.repository.AuthorComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorComicServices {

    @Autowired
    private AuthorComicRepository authorComicRepository;

    public List<AuthorComic> findAll() {
        return authorComicRepository.findAll();
    }

    public Optional<AuthorComic> findById(Long id) {
        return authorComicRepository.findById(id);
    }

    public Optional<AuthorComic> findByIdComic(Long id) { return authorComicRepository.findByIdComic(id); }

    public AuthorComic save(AuthorComic authorComic){ return authorComicRepository.save(authorComic); }

    @Transactional
    public AuthorComic update(Long id, int timeDedicated){
        AuthorComic update = authorComicRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Relation not found")));

        if(timeDedicated > 0){
            update.setTimeDedicated(timeDedicated);
        }

        return update;
    }

    @Transactional
    public void delete(Long id){

        AuthorComic delete = authorComicRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Relation not found")));

        authorComicRepository.deleteById(delete.getId());
    }
}
