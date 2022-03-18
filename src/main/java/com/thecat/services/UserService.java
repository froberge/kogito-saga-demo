package com.thecat.services;


import javax.enterprise.context.ApplicationScoped;

import com.thecat.util.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Response getProfile( String transferId ) {
        LOGGER.info( " Get User Profile for transfer {}", transferId);
        return Response.success(transferId);
    }
}