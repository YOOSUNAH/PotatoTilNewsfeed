package com.example.potatotilnewsfeed.domain.user.repository;

import com.example.potatotilnewsfeed.domain.user.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
