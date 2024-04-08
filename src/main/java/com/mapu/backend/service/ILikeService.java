package com.mapu.backend.service;

import java.util.List;

import com.mapu.backend.exception.TwitException;
import com.mapu.backend.exception.UserException;
import com.mapu.backend.model.Like;
import com.mapu.backend.model.User;

public interface ILikeService {

    public Like likeTwit(Long twitId, User user) throws UserException, TwitException;

    public List<Like> getAllLikes(Long twitId) throws TwitException;
}
