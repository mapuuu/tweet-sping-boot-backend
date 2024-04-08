package com.mapu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapu.backend.exception.TwitException;
import com.mapu.backend.exception.UserException;
import com.mapu.backend.model.Like;
import com.mapu.backend.model.Twit;
import com.mapu.backend.model.User;
import com.mapu.backend.repository.ILikeRepository;
import com.mapu.backend.repository.ITwitRepository;

@Service
public class LikeServiceImplementation implements ILikeService {

    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    private ITwitService twitService;

    @Autowired
    private ITwitRepository twitRepository;

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitException {
        Twit twit = twitService.findById(twitId);
        List<Like> likes = likeRepository.findByTwitId(twitId);

        return likes;
    }

    @Override
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException {
        Like isLikeExist = likeRepository.isLikeExist(user.getId(), twitId);

        if (isLikeExist != null) {
            likeRepository.deleteById(isLikeExist.getId());
            return isLikeExist;
        }

        Twit twit = twitService.findById(twitId);

        Like like = new Like();
        like.setTwit(twit);
        like.setUser(user);

        Like savedLike = likeRepository.save(like);
        twit.getLikes().add(savedLike);
        twitRepository.save(twit);
        return savedLike;
    }

}
