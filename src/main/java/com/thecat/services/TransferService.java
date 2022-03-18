package com.thecat.services;

import javax.enterprise.context.ApplicationScoped;

import com.thecat.util.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    public Response success(String transferId) {
        LOGGER.info("Transfer Success for transfer {}", transferId);
        return Response.success(transferId);
    }

    public Response failure(String transferId) {
        LOGGER.info("Transfer Failed for transfer {}", transferId);
        return Response.error(transferId);
    }
}