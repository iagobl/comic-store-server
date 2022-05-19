package com.iagobl.server.repository;

import com.iagobl.server.model.AuthorComic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorComicRepository extends JpaRepository<AuthorComic, Long> {
}
