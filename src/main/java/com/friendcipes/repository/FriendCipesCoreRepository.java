package com.friendcipes.repository;

import com.friendcipes.utils.DBUtil;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendCipesCoreRepository {

    private final DBUtil dbUtil;

    @Inject
    public FriendCipesCoreRepository(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }

    public String getTimeStamp() throws SQLException {
        Connection conn = dbUtil.establishConnection();
        PreparedStatement getTimeStamp = conn.prepareStatement("SELECT CURRENT_TIMESTAMP");
        ResultSet rs = getTimeStamp.executeQuery();
        return rs.getString("now");
    }

}
