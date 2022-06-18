package com.iagobl.server.repository;

import com.iagobl.server.model.AuthorComic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorComicRepository extends JpaRepository<AuthorComic, Long> {
    @Query("SELECT a FROM AuthorComic a WHERE a.comic.id = :id")
    Optional<AuthorComic> findByIdComic(@Param("id") Long id);
}
