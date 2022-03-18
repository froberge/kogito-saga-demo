package com.thecat.clients;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.core.Response;

import com.thecat.util.ServiceUtil;

@ApplicationScoped
public class MockRemoteService {
    
    @Inject
    private ServiceUtil serviceUtil;

    @ConfigProperty(name = "service.transaction.latency")
    int transactionLatency;

    @ConfigProperty(name = "service.transaction.error")
    boolean transactionError;

    @ConfigProperty(name = "service.fraud.latency")
    int fraudLatency;

    @ConfigProperty(name = "service.fraud.error")
    boolean fraudError;

    @ConfigProperty(name = "service.payment.latency")
    int paymentLatency;

    @ConfigProperty(name = "service.payment.error")
    boolean paymentError;


    public int validFraud() {
        serviceUtil.addLatency(fraudLatency);
        return fraudError ? 222: Response.Status.OK.getStatusCode() ;
    }


    public int initTransaction() {
        serviceUtil.addLatency(transactionLatency);
        return transactionError ? 222: Response.Status.OK.getStatusCode() ;
    }

    public int makePayment() {
        serviceUtil.addLatency(paymentLatency);
        return paymentError ? 222: Response.Status.OK.getStatusCode() ;
    }

}
