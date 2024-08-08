package com.friendcipes.routing;

import com.friendcipes.exception.PathNotDefinedException;
import com.friendcipes.services.FriendCipesCoreServiceImpl;

import javax.inject.Inject;
import java.sql.SQLException;

public class GetRouter {

    private FriendCipesCoreServiceImpl friendCipesCoreService;

    @Inject
    public GetRouter(FriendCipesCoreServiceImpl friendCipesCoreService){
        this.friendCipesCoreService = friendCipesCoreService;
    }

    public String handle(String requestPath) throws PathNotDefinedException, SQLException {
        switch(requestPath){
            case "/pig":
                return friendCipesCoreService.handlePig();
            case "/cow":
                return friendCipesCoreService.handleCow();
            case "/timestamp":
                return friendCipesCoreService.getTimeStamp();
        }
        throw new PathNotDefinedException("Path is undefined for http method GET!");
    }
}
