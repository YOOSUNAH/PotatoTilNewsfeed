package com.example.potatotilnewsfeed.domain.user.repository;

import static com.example.potatotilnewsfeed.domain.user.entity.QUser.user;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        JPQLQuery<User> query = queryFactory.selectFrom(user)
            .where(eqNickname(nickname));
        List<User> users = query.fetch();
        return users.stream().findFirst();
    }

    @Override
    public List<User> search(String nickname, String email) {
        return queryFactory
            .selectFrom(user)
            .where(eqNickname(nickname), containEmail(email))
            .fetch();
    }

    private BooleanExpression eqNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            return null;
        }
        return user.nickname.eq(nickname);
    }

    private Predicate containEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return user.email.containsIgnoreCase(email);
    }


}
