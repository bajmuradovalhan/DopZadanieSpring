package org.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RestClient {
    public PaymentResult postRequestPayment(String url, UserOrder order) throws IOException {
        HttpPost post = new HttpPost(url);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        post.setEntity(new StringEntity(jsonString));

        String payResultJson;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            payResultJson = EntityUtils.toString(response.getEntity());
        }
        PaymentResult paymentResult = objectMapper.readValue(payResultJson, PaymentResult.class);
        return paymentResult;
    }
}
