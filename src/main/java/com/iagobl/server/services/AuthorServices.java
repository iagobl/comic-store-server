package com.iagobl.server.services;

import com.iagobl.server.model.Author;
import org.springframework.http.HttpStatus;
import com.iagobl.server.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServices {

    @Autowired
    private  AuthorRepository AuthorRepository;

    public List<Author> findAll(){
        return AuthorRepository.findAll();
    }

    public Optional<Author> findById(Long id){
        return AuthorRepository.findById(id);
    }

    public Optional<Author> findByName(String name){
        return AuthorRepository.findByName(name);
    }

    public Author authorSave(Author author){
        Optional<Author> search = findByName(author.getName());
        if(search.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND, String.format("Author found"));
        }

        return AuthorRepository.save(author);
    }

    @Transactional
    public Author authorImageUpdate(Long id, MultipartFile imageAuthor) {
        Author update = AuthorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author not found")));

        try {
            update.setImage(imageAuthor.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return update;
    }

    @Transactional
    public Author authorUpdate(Author author){

        Author update = AuthorRepository.findById(author.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author not found")));
        Optional<Author> authorSearch = AuthorRepository.findByName(author.getName());
        if(authorSearch.isPresent()){
            throw new ResponseStatusException(HttpStatus.FOUND, String.format("Author name repeat"));
        }

        if (!author.getName().isEmpty() && !author.getName().isBlank()){
            update.setName(author.getName());
        }

        if (!author.getSurname().isEmpty() && !author.getSurname().isBlank()){
            update.setSurname(author.getSurname());
        }

        return update;
    }

    @Transactional
    public void authorDelete(Long id){
        AuthorRepository.deleteById(id);
    }
}
