package com.example.potatotilnewsfeed.domain.user.repository;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByNickname(String nickname);


    List<User> search(String nickname, String email);


}
