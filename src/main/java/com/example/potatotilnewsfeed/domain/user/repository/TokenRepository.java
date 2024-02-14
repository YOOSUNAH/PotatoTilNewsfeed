package com.example.potatotilnewsfeed.domain.user.repository;

import com.example.potatotilnewsfeed.domain.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {

}
