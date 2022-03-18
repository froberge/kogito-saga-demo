package com.thecat.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.thecat.clients.MockRemoteService;
import com.thecat.util.Response;
import com.thecat.util.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FraudService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FraudService.class);

    @Inject
    ServiceUtil serviceUtil;


    @Inject
    private MockRemoteService remoteService;


    public Response checkFraud(String transferId) {
        return this.checkFraud(transferId, false);
    }

    public Response checkFraud(String transferId, boolean handleException) {
        LOGGER.info("Check Fraud for transfer {}", transferId);

        boolean isFail = false;
        int statusCode = remoteService.validFraud();
        
        if (statusCode != 200 ) {
            isFail = true;
        }

        return serviceUtil.createResponse(transferId, isFail, handleException, FraudService.class); 
    }

    public Response cancelFraud(String transferId) {
        LOGGER.info("Cancel Fraud for transfer {}", transferId);
        return Response.error(transferId);
    }
}