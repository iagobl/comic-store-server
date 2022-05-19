package com.iagobl.server.repository;

import com.iagobl.server.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("SELECT a FROM Collection a  where a.name = :name")
    Optional<Collection> findByName(@Param("name") String name);
}
