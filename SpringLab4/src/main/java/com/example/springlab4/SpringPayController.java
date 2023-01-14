package com.example.springlab4;

import com.example.springlab4.model.PaymentResult;
import com.example.springlab4.model.UserOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringPayController {

    @PostMapping(value = "/paymentSpring")
    public ResponseEntity<PaymentResult> create(@RequestBody UserOrder userOrder) {
        SpringPayService paymentService = new SpringPayService();
        PaymentResult paymentResult = paymentService.buyFurniture(userOrder);
        return new ResponseEntity<>(paymentResult, HttpStatus.CREATED);
    }
}
