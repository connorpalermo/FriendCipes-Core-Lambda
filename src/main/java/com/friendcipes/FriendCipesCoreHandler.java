package com.friendcipes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.friendcipes.config.DaggerConfigComponent;
import com.friendcipes.exception.PathNotDefinedException;
import com.friendcipes.model.HttpConstants;
import com.friendcipes.routing.GetRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class FriendCipesCoreHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    GetRouter gr = DaggerConfigComponent.create().buildGetRouter();
    private static final Logger logger = LoggerFactory.getLogger(FriendCipesCoreHandler.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        String httpMethod = event.getHttpMethod();
        String httpPath = event.getPath();
        logger.info("METHOD IS: {}", httpMethod);
        logger.info("Request path is {}", httpPath);
        switch(httpMethod){
            case HttpConstants.GET:
                String responseBody;
                try {
                    responseBody = gr.handle(httpPath);
                } catch(PathNotDefinedException | SQLException p){
                    response.setBody(p.getMessage());
                    response.setStatusCode(HttpConstants.INTERNAL_SERVER_ERROR);
                    return response;
                }
                response.setBody(responseBody);
                response.setStatusCode(HttpConstants.OK);
                break;
            case HttpConstants.DELETE:
            case HttpConstants.POST:
                response.setBody("Not yet implemented.");
                response.setStatusCode(HttpConstants.NOT_FOUND);
                break;
            default:
                response.setBody("Unable to handle http method.");
                response.setStatusCode(HttpConstants.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
