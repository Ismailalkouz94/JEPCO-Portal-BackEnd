package com.lg.JepcoCsPortal.controller;

import com.lg.JepcoCsPortal.services.CustomerProfileService;
import com.lg.JepcoCsPortal.utils.MessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping("/customerProfile/findAll")
    public ResponseEntity<MessageBody> findAllCustomers()
    {
        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setBody(customerProfileService.findAll());
        messageBody.setKey("success");
        messageBody.setStatus("200");

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

}
