package com.disneyworld.demo.repositories;

import com.disneyworld.demo.entities.Films;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Long> {

    public List<Films> findAll(Specification<Films> filtered);
}
