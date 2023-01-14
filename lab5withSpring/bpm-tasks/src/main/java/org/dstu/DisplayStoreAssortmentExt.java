package org.dstu;

import org.camunda.bpm.client.ExternalTaskClient;
import org.soap1.StoreClient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.soap1.StoreClient.getWSDLURL;

public class DisplayStoreAssortmentExt {
    private final static Logger LOGGER = Logger.getLogger(DisplayStoreAssortmentExt.class.getName());
    private final static String Furniture_TYPE = "Table cafe";
    private final static int QUANTITY_RESERVE = 1;

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000)
                .build();

        client.subscribe("show-assortment")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    LOGGER.info("Stage show-assortment");

                    URL url = getWSDLURL("http://localhost:8081/StoreSvcWeb/displayassortment?wsdl");
                    StoreClient storeClient = new StoreClient();
                    storeClient.process(url);

                    Map<String, Object> outputData = new HashMap<>();
                    outputData.put("furnitureModel", Furniture_TYPE);
                    outputData.put("quantityReserve", QUANTITY_RESERVE);
                    externalTaskService.complete(externalTask, outputData);
                })
                .open();
    }
}
