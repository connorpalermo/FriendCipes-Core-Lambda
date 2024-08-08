package com.friendcipes.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class SecretsUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecretsUtil.class);
    private final SecretsManagerClient secretsManagerClient;

    public SecretsUtil(SecretsManagerClient secretsManagerClient){
        this.secretsManagerClient = secretsManagerClient;
    }

    public String getSecret(String secretName){
        String secret = "";
        logger.info("Trying to get secret!");
        try{
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();
            GetSecretValueResponse valueResponse = secretsManagerClient.getSecretValue(valueRequest);
            secret = valueResponse.secretString();
        } catch (SecretsManagerException e){
            logger.info("Error retrieving secret! Message: {}", e.getMessage());
        }

        return secret;
    }
}
