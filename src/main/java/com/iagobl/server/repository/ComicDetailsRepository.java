package com.iagobl.server.repository;

import com.iagobl.server.model.ComicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicDetailsRepository extends JpaRepository<ComicDetails, Long> {
}
