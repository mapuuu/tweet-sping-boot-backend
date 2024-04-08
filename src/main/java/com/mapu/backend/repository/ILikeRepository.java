package com.mapu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mapu.backend.model.Like;

public interface ILikeRepository extends JpaRepository<Like, Long> {

    @Query("select l from Like l where l.user.id = :userId and l.twit.id = :twitId")
    public Like isLikeExist(@Param("userId") Long userId, @Param("twitId") Long twitId);

    @Query("select l from Like l where l.twit.id = :twitId")
    public List<Like> findByTwitId(@Param("twitId") Long twitId);
}
