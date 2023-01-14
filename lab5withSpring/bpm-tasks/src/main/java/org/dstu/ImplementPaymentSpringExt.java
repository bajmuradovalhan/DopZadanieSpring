package org.dstu;

import org.camunda.bpm.client.ExternalTaskClient;
import org.rest.PaymentResult;
import org.rest.RestClient;
import org.rest.UserOrder;
import org.soap2.UserReserve;

import java.awt.*;
import java.net.URI;
import java.util.logging.Logger;

public class ImplementPaymentSpringExt {
    private final static Logger LOGGER = Logger.getLogger(implementPaymentExt.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000)
                .build();

        client.subscribe("implement-payment-spring")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    LOGGER.info("Stage implement-payment-spring");
                    UserReserve reserve = (UserReserve) externalTask.getVariable("UserReserve");

                    UserOrder order = new UserOrder();
                    order.setProductId(reserve.getFurniture().getId());
                    order.setQuantity(reserve.getQuantity());

                    try {
                        String urlSpring = "http://localhost:8088/paymentSpring";
                        RestClient restClient = new RestClient();
                        PaymentResult paymentResult = restClient.postRequestPayment(urlSpring, order);
                        System.out.println("Payment completed");
                        System.out.println(paymentResult.getProductInfo() + " " +
                                "| quantity: " + paymentResult.getQuantity() + " " +
                                "| total sum: " + paymentResult.getPrice() + " " +
                                "| result info: " + paymentResult.getResultMessage());

                        Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LOGGER.info("Completed");
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
