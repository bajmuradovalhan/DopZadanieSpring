package com.example.springlab4;

import com.example.springlab4.model.Furniture;
import com.example.springlab4.model.PaymentResult;
import com.example.springlab4.model.StoreAssortment;
import com.example.springlab4.model.UserOrder;
import org.springframework.stereotype.Service;

@Service
public class SpringPayService {
    private static Furniture[] furnitureData = {
            new Furniture("1", "IKEA", "Chair", "Office chair", "10", 8000),
            new Furniture("2", "Ashley HomeStore", "Table", "Coffee table", "25", 15000),
            new Furniture("3", "Restoration Hardware", "Chair", "Dining chair", "7", 1200),
            new Furniture("4", "Kartell", "Table", "Console table", "20", 17000),
            new Furniture("5", "Williams-Sonoma ", "Chair", "Chair for home", "5", 7000)
    };
    private static int[] quantityData = {0, 2, 3, 5, 1};
    private static StoreAssortment storeAssortment = new StoreAssortment(furnitureData, quantityData);

    public PaymentResult buyFurniture(UserOrder userOrder) {
        int size = storeAssortment.getFurnitures().length;
        for (int i = 0; i < size; i++) {
            Furniture currentFurniture = storeAssortment.getFurnitures()[i];
            if (currentFurniture.getId().equals(userOrder.getProductId())) {
                double orderPrice = currentFurniture.getPrice() * userOrder.getQuantity();
                return new PaymentResult(currentFurniture.toString(), userOrder.getQuantity(), orderPrice,
                        "Buy successful!");
            }
        }
        return new PaymentResult(userOrder.getProductId(), userOrder.getQuantity(), 0,
                "Buy failure! Model with id = " + userOrder.getProductId() + " not found");
    }
}
