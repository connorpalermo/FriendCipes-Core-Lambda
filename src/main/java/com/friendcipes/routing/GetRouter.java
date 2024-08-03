package com.friendcipes.routing;

import com.friendcipes.exception.PathNotDefinedException;
import com.friendcipes.services.FriendCipesCoreServiceImpl;

public class GetRouter {

    private final FriendCipesCoreServiceImpl friendCipesCoreService = new FriendCipesCoreServiceImpl();

    public String handle(String requestPath) throws PathNotDefinedException {
        switch(requestPath){
            case "/pig":
                return friendCipesCoreService.handlePig();
            case "/cow":
                return friendCipesCoreService.handleCow();
        }
        throw new PathNotDefinedException("Path is undefined for http method GET!");
    }
}
