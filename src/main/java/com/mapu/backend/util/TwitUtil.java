package com.mapu.backend.util;

import com.mapu.backend.model.Like;
import com.mapu.backend.model.Twit;
import com.mapu.backend.model.User;

public class TwitUtil {

    public final static boolean isLikedByReqUser(User reqUser, Twit twit) {

        for (Like like : twit.getLikes()) {
            if (like.getUser().getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }

    public final static boolean isRetwitedByReqUser(User reqUser, Twit twit) {

        for (User user : twit.getRetwitUser()) {
            if (user.getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }
}
