package com.example.potatotilnewsfeed.domain.til.repository;

import com.example.potatotilnewsfeed.domain.til.entity.TilLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TilLikeRepository extends JpaRepository<TilLike, Long> , TilLikeRepositoryCustom{


}
