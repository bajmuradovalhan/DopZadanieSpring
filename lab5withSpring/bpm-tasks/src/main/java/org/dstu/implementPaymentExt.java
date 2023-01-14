package org.dstu;

import org.camunda.bpm.client.ExternalTaskClient;
import org.rest.PaymentResult;
import org.rest.RestClient;
import org.rest.UserOrder;
import org.soap2.UserReserve;

import java.awt.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class implementPaymentExt {

    private final static Logger LOGGER = Logger.getLogger(implementPaymentExt.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000)
                .build();

        client.subscribe("implement-payment")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    LOGGER.info("Stage implement-payment");
                    UserReserve reserve = (UserReserve) externalTask.getVariable("UserReserve");

                    UserOrder order = new UserOrder();
                    order.setProductId(reserve.getFurniture().getId());
                    order.setQuantity(reserve.getQuantity());

                    try {
                        String urlJersey = "http://localhost:8083/RestExample/rest/shop/payment";
                        RestClient restClient = new RestClient();
                        PaymentResult paymentResult = restClient.postRequestPayment(urlJersey, order);
                        System.out.println("Payment completed");
                        System.out.println(paymentResult.getProductInfo() + " " +
                                "| quantity: " + paymentResult.getQuantity() + " " +
                                "| total sum: " + paymentResult.getPrice() + " " +
                                "| result info: " + paymentResult.getResultMessage());

                        // Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Map<String, Object> outputData = new HashMap<>();
                    outputData.put("UserReserve", reserve);
                    externalTaskService.complete(externalTask, outputData);
                })
                .open();
    }
}
