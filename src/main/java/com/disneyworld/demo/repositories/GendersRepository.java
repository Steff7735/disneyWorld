package com.disneyworld.demo.repositories;

import com.disneyworld.demo.entities.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GendersRepository extends JpaRepository<Genders, Long> {
}
