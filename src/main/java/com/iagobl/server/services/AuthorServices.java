package com.iagobl.server.services;

import com.iagobl.server.model.Author;
import org.springframework.http.HttpStatus;
import com.iagobl.server.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthorServices {

    @Autowired
    private  AuthorRepository AuthorRepository;

    public Optional<Author> findById(Long id){
        return AuthorRepository.findById(id);
    }

    public Optional<Author> findByName(String name){
        return AuthorRepository.findByName(name);
    }

    public Author authorSave(Author author){
        return AuthorRepository.save(author);
    }

    @Transactional
    public Author authorUpdate(Long id, String name, String surname){

        Author update = AuthorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author not found")));

        if (!name.isEmpty() && !name.isBlank()){
            update.setName(name);
        }

        if (!surname.isEmpty() && !surname.isBlank()){
            update.setSurname(surname);
        }

        return update;
    }

    @Transactional
    public void authorDelete(Long id){
        AuthorRepository.deleteById(id);
    }
}
