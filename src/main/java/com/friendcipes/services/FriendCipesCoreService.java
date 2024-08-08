package com.friendcipes.services;

import java.sql.SQLException;

public interface FriendCipesCoreService {
    String handleCow();
    String handlePig();
    String getTimeStamp() throws SQLException;
}
