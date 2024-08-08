package com.friendcipes.config;

import com.friendcipes.repository.FriendCipesCoreRepository;
import com.friendcipes.routing.GetRouter;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = ConfigModule.class)
public interface ConfigComponent {

    FriendCipesCoreRepository buildFriendcipesCoreRepository();
    GetRouter buildGetRouter();
}
