package com.example.potatotilnewsfeed.domain.til.repository;

import com.example.potatotilnewsfeed.domain.til.entity.Til;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TilRepository extends JpaRepository<Til, Long> {

}
