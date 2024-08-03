package com.friendcipes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.friendcipes.exception.PathNotDefinedException;
import com.friendcipes.model.HttpConstants;
import com.friendcipes.routing.GetRouter;

public class FriendCipesCoreHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    GetRouter gr = new GetRouter();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        switch(event.getHttpMethod()){
            case HttpConstants.GET:
                String responseBody;
                try {
                    responseBody = gr.handle(event.getPath());
                } catch(PathNotDefinedException p){
                    response.setBody(p.getMessage());
                    response.setStatusCode(HttpConstants.INTERNAL_SERVER_ERROR);
                    return response;
                }
                response.setBody(responseBody);
                response.setStatusCode(HttpConstants.OK);
            case HttpConstants.DELETE:
                response.setBody("Not yet implemented.");
                response.setStatusCode(HttpConstants.NOT_FOUND);
            case HttpConstants.POST:
                response.setBody("Not yet implemented.");
                response.setStatusCode(HttpConstants.NOT_FOUND);
            default:
                response.setBody("Unable to handle http method.");
                response.setStatusCode(HttpConstants.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
