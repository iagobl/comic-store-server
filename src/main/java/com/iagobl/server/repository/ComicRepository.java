package com.iagobl.server.repository;

import com.iagobl.server.model.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    @Query("SELECT a FROM Comic a where a.name = :name")
    Optional<Comic> findByName(@Param("name") String name);
}
