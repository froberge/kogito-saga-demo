package com.thecat.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.thecat.clients.MockRemoteService;
import com.thecat.util.Response;
import com.thecat.util.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Inject
    ServiceUtil serviceUtil;

    @Inject
    private MockRemoteService remoteService;

    public Response makePayment(String transferId) {
        return this.makePayment(transferId, false);
    }

    public Response makePayment(String transferId, boolean handleException) {
        LOGGER.info("Process Payment for transfer {}", transferId);
        boolean isFail = false;
        int statusCode = remoteService.makePayment();
        
        if (statusCode != 200 ) {
            isFail = true;
        }

        return serviceUtil.createResponse(transferId, isFail, handleException, FraudService.class); 
    }

    public Response cancelPayment(String transferId) {
        LOGGER.info("Cancel Payment for transfer {}", transferId);
        return Response.error(transferId);
    }
}