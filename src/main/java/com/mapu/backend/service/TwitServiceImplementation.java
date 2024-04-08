package com.mapu.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapu.backend.exception.TwitException;
import com.mapu.backend.exception.UserException;
import com.mapu.backend.model.Twit;
import com.mapu.backend.model.User;
import com.mapu.backend.repository.ITwitRepository;
import com.mapu.backend.request.TwitReplyRequest;

@Service
public class TwitServiceImplementation implements ITwitService {

    @Autowired
    private ITwitRepository twitRepository;

    @Override
    public Twit createTwit(Twit req, User user) throws UserException {
        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUser(user);
        twit.setReply(false);
        twit.setTwit(true);
        twit.setVideo(req.getVideo());

        return twitRepository.save(twit);
    }

    @Override
    public Twit createdReply(TwitReplyRequest req, User user) throws TwitException {

        Twit replyFor = findById(req.getTwitId());

        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUser(user);
        twit.setReply(true);
        twit.setTwit(false);
        twit.setReplyFor(replyFor);

        Twit savedReply = twitRepository.save(twit);
        twit.getReplyTwits().add(savedReply);
        twitRepository.save(replyFor);

        return replyFor;
    }

    @Override
    public void deleteTwitById(Long twitId, Long userId) throws UserException, TwitException {
        Twit twit = findById(twitId);
        if (!userId.equals(twit.getUser().getId())) {
            throw new UserException("You can't delete another user's twit");
        }
        twitRepository.deleteById(twit.getId());
    }

    @Override
    public List<Twit> findAllTwit() {

        return twitRepository.findAllByIsTwitTrueOrderByCreatedAtDesc();
    }

    @Override
    public Twit findById(Long twitId) throws TwitException {
        Twit twit = twitRepository.findById(twitId)
                .orElseThrow(() -> new TwitException("Twit not found with id " + twitId));
        return twit;
    }

    @Override
    public List<Twit> findByLikesContainsUser(User user) {

        return twitRepository.findByLikesUser_id(user.getId());
    }

    @Override
    public List<Twit> getUserTwit(User user) {

        return twitRepository.findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(user, user.getId());
    }

    @Override
    public Twit removeFromRetwit(Long twitId, User user) throws UserException, TwitException {

        return null;
    }

    @Override
    public Twit retwit(Long twitId, User user) throws UserException, TwitException {
        Twit twit = findById(twitId);
        if (twit.getRetwitUser().contains(user)) {
            twit.getRetwitUser().remove(user);
        } else {
            twit.getRetwitUser().add(user);
        }
        return twitRepository.save(twit);
    }

}
