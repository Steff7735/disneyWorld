package com.disneyworld.demo.repositories;

import com.disneyworld.demo.entities.Characters;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {

    public List<Characters> findAll(Specification<Characters> filtered);
}
