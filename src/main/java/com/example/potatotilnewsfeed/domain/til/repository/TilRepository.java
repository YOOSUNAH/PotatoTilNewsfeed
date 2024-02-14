package com.example.potatotilnewsfeed.domain.til.repository;

import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TilRepository extends JpaRepository<Til, Long> {

    List<Til> findAllByUser(User user);
}
