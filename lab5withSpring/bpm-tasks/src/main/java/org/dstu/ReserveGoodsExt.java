package org.dstu;

import org.camunda.bpm.client.ExternalTaskClient;
import org.soap2.ReservationClient;
import org.soap2.UserReserve;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.soap2.ReservationClient.getWSDLURL;

public class ReserveGoodsExt {
    private final static Logger LOGGER = Logger.getLogger(ReserveGoodsExt.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000)
                .build();

        client.subscribe("reserve-goods")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String furnitureModel = (String) externalTask.getVariable("furnitureModel");
                    int quantityReserve = (int) externalTask.getVariable("quantityReserve");

                    LOGGER.info("Stage reserve-goods");
                    LOGGER.info("Trying reserves " +
                            "furniture model = " + furnitureModel + " quantity = " + quantityReserve);

                    URL url = getWSDLURL("http://localhost:8082/Reservation?wsdl");
                    ReservationClient resClient = new ReservationClient();
                    UserReserve userReserve = resClient.processCallback(url, furnitureModel, quantityReserve);

                    Map<String, Object> outputData = new HashMap<>();

                    if (userReserve != null) {
                        LOGGER.info("Product reserve");
                        outputData.put("reserved", true);
                        outputData.put("UserReserve", userReserve);
                        resClient.printUserReserve(userReserve);
                        externalTaskService.complete(externalTask, outputData);
                    } else {
                        LOGGER.info("Not added ");
                        outputData.put("reserved", false);
                        externalTaskService.complete(externalTask, outputData);
                    }
                })
                .open();
    }
}