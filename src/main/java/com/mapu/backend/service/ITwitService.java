package com.mapu.backend.service;

import java.util.List;

import com.mapu.backend.exception.TwitException;
import com.mapu.backend.exception.UserException;
import com.mapu.backend.model.Twit;
import com.mapu.backend.model.User;
import com.mapu.backend.request.TwitReplyRequest;

public interface ITwitService {
    public Twit createTwit(Twit req, User user) throws UserException;

    public List<Twit> findAllTwit();

    public Twit retwit(Long twitId, User user) throws UserException, TwitException;

    public Twit findById(Long twitId) throws TwitException;

    public void deleteTwitById(Long twitId, Long userId) throws UserException, TwitException;

    public Twit removeFromRetwit(Long twitId, User user) throws UserException, TwitException;

    public Twit createdReply(TwitReplyRequest req, User user) throws TwitException;

    public List<Twit> getUserTwit(User user);

    public List<Twit> findByLikesContainsUser(User user);
}
