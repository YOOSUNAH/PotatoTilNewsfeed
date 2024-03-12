package com.example.potatotilnewsfeed.domain.til.repository;

import static com.example.potatotilnewsfeed.domain.til.entity.QTil.til;
import static com.example.potatotilnewsfeed.domain.til.entity.QTilLike.tilLike;
import static com.example.potatotilnewsfeed.domain.user.entity.QUser.user;

import com.example.potatotilnewsfeed.domain.til.entity.QTil;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.entity.TilLike;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TilLikeRepositoryImpl extends QuerydslRepositorySupport implements
    TilLikeRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public TilLikeRepositoryImpl() {
        super(TilLike.class);
    }

    /*
     select * from TilLike where user = 'param - user'
      */
    @Override
    public Optional<List<TilLike>> findAllByUser(User user) {
        List<TilLike> tilLikes = jpaQueryFactory.selectFrom(tilLike)
            .where(equalNickname(user.getNickname()))
            .fetch();

        return Optional.ofNullable(tilLikes);
    }

    /*
     select * from TilLike where user = 'param - user' and Til = 'param -Til'
      */
    @Override
    public Optional<TilLike> findByTilAndUser(Til til, User user) {
        return jpaQueryFactory.selectFrom(tilLike)
            .where(tilLike.user.nickname.eq(user.getNickname()), equalTilId(til.getId()))
            .fetch()
            .stream()
            .findFirst();
    }

    @Override
    public List<TilLike> findAllByTil(Til til) {
        return jpaQueryFactory
            .selectFrom(tilLike)
            .where(equalTilId(til.getId()))
            .fetch();
    }

    private BooleanExpression equalNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            return null;
        }
        return user.nickname.eq(nickname);
    }

    private BooleanExpression equalTilId(Long tilId) {
        if (tilId == null) {
            return null;
        }
        return til.id.eq(tilId);
    }

    @Override
    public List<Til> getTilListWithPage(long offset, int pageSize) {

        QTil tils = QTil.til;

        return jpaQueryFactory.selectFrom(tils)
            .offset(offset)
            .limit(pageSize)
            .fetch();
    }


    @Override
    public List<Til> getAllTilPageAndSortCreateAtDesc(long offset, int pageSize) {

        QTil tils = QTil.til;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC,
            tils.createdAt);  // order를 내림차순 정렬하고, tils.createdAt 을 기준으로

        return jpaQueryFactory.selectFrom(tils)
            .offset(offset)
            .limit(pageSize)
            .orderBy(orderSpecifier)
            .fetch();
    }

}
