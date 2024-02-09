package com.example.potatotilnewsfeed.domain.til.repository;

import com.example.potatotilnewsfeed.domain.til.entity.TilLike;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TilLikeRepository extends JpaRepository<TilLike, Long> {

    Optional<List<TilLike>> findAllByUser(User user);
}
