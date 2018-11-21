package com.lg.JepcoCsPortal.controller;

import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.helpers.Login;
import com.lg.JepcoCsPortal.services.CustomerProfileService;
import com.lg.JepcoCsPortal.utils.MessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping("/customerProfile/find/{customerId}")
    public ResponseEntity<MessageBody> findCustomerById(@PathParam("customerId") Long customerId)
    {
        MessageBody messageBody = MessageBody.getInstance();
        messageBody.setBody(customerProfileService.find(customerId));
        messageBody.setKey("success");
        messageBody.setStatus("200");

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/register")
    public ResponseEntity<MessageBody> registerCustomer(@RequestBody CustomerProfile customerProfile) {

        MessageBody messageBody = MessageBody.getInstance();
        messageBody.setBody(customerProfileService.save(customerProfile));
        messageBody.setKey("success");
        messageBody.setStatus("200");

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/login")
    public ResponseEntity<MessageBody> loginCustomer(@RequestBody Login login) {

        MessageBody messageBody = MessageBody.getInstance();
        messageBody.setBody(customerProfileService.login(login));
        messageBody.setKey("success");
        messageBody.setStatus("200");

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

}
