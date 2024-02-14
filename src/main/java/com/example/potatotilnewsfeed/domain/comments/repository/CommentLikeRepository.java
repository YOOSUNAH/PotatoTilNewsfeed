package com.example.potatotilnewsfeed.domain.comments.repository;

import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

  Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
