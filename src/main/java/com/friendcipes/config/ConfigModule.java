package com.friendcipes.config;

import com.friendcipes.services.FriendCipesCoreServiceImpl;
import com.friendcipes.utils.DBUtil;
import com.friendcipes.utils.SecretsUtil;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import javax.inject.Singleton;

@Module
public class ConfigModule {
    private ConfigModule(){
        throw new UnsupportedOperationException("Utility Class");
    }

    @Provides
    @Singleton
    public static FriendCipesCoreServiceImpl friendCipesCoreService(){
        return new FriendCipesCoreServiceImpl();
    }

    @Provides
    @Singleton
    public static SecretsUtil secretsUtil(){
        Region region = Region.US_EAST_1;
        return new SecretsUtil(SecretsManagerClient.builder()
                .region(region)
                .build());
    }

    @Provides
    @Singleton
    public static DBUtil dbUtil(){
        return new DBUtil(secretsUtil());
    }

}
