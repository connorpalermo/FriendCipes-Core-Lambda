package com.friendcipes.services;

import com.friendcipes.config.DaggerConfigComponent;
import com.friendcipes.repository.FriendCipesCoreRepository;

import java.sql.SQLException;

public class FriendCipesCoreServiceImpl implements FriendCipesCoreService{
    private FriendCipesCoreRepository cr = DaggerConfigComponent.create().buildFriendcipesCoreRepository();
    @Override
    public String handleCow() {
        return "MOOOOOOO!!";
    }

    @Override
    public String handlePig() {
        return "OINK!!!!!";
    }

    @Override
    public String getTimeStamp() throws SQLException {
        return cr.getTimeStamp();
    }

}
