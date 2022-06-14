package com.iagobl.server.services;

import com.iagobl.server.model.Collection;
import com.iagobl.server.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionServices {

    @Autowired
    private CollectionRepository collectionRepository;

    public List<Collection> findAll(){
        return collectionRepository.findAll();
    }

    public Optional<Collection> findById(Long id) {
        return collectionRepository.findById(id);
    }

    public Optional<Collection> findByName(String name){
        return collectionRepository.findByName(name);
    }

    public Collection collectionSave(Collection collection){
        return collectionRepository.save(collection);
    }

    @Transactional
    public Collection collectionImageUpdate(Long id, MultipartFile imageCollection) {
        Collection update = collectionRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found")));

        try {
            update.setImage(imageCollection.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return update;
    }

    @Transactional
    public Collection collectionUpdate(Long id, String name, String editorial){

        Collection update = collectionRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found")));

        if(!name.isEmpty() && !name.isBlank()){
            update.setName(name);
        }

        if(!editorial.isEmpty() && !editorial.isBlank()){
            update.setEditorial(editorial);
        }

        return update;
    }

    @Transactional
    public void collectionDelete(Long id){

        Collection delete = collectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Collection not found")));

        collectionRepository.deleteById(delete.getId());
    }
}
