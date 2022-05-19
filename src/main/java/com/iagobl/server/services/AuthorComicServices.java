package com.iagobl.server.services;

import com.iagobl.server.model.AuthorComic;
import com.iagobl.server.repository.AuthorComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AuthorComic save(AuthorComic authorComic){
        return authorComicRepository.save(authorComic);
    }

    @Transactional
}
