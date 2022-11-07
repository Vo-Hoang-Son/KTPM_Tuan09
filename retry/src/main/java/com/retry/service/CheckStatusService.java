package com.retry.service;

import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class CheckStatusService {
	@Retryable(value = HttpStatusCodeException.class, maxAttempts = 3, backoff = @Backoff(3000), exclude =
            HttpClientErrorException.class)
    public String checkStatus(String trackingNumber) {
        System.out.println("Connecting... ");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Recover
    public String recover(HttpServerErrorException exception) {
        return "Hello";
    }
}
