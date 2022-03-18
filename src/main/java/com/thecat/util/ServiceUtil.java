package com.thecat.util;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ServiceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtil.class);
    
    public Response createResponse(String transferId, boolean isFail, boolean handleException, Class clazz ) {
        if (isFail && handleException ) {
            LOGGER.info("Error in {} for transfer {}, need to rollback", clazz, transferId);
            throw new ServiceException("Error executing " + clazz );
        }
        else {
            return new Response( isFail ? Response.Type.ERROR : Response.Type.SUCCESS, transferId );
        }
    }

    /**
     * Add a method to simulate the latency the API could have
     * @param the number of second to sleep.
     */
    public void addLatency(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
