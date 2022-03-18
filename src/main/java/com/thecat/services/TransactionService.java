package com.thecat.services;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.thecat.clients.MockRemoteService;
import com.thecat.util.Response;
import com.thecat.util.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Inject
    ServiceUtil serviceUtil;

    @Inject
    private MockRemoteService remoteService;

    public Response initTransaction(String transferId) {
        return this.initTransaction(transferId, false );
    }

    public Response initTransaction(String transferId, boolean handleException) {
        LOGGER.info("Init Transaction for transfer {}", transferId);
        boolean isFail = false;
        int statusCode = remoteService.initTransaction();
        
        if (statusCode != 200 ) {
            isFail = true;
        }

        return serviceUtil.createResponse(transferId, isFail, handleException, FraudService.class); 
    }

    public Response cancelTransaction(String transferId) {
        LOGGER.info("Cancel Transaction for transfer {}", transferId);
        return Response.error(transferId);
    }
}