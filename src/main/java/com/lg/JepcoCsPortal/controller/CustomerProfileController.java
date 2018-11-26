package com.lg.JepcoCsPortal.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.helpers.Login;
import com.lg.JepcoCsPortal.services.CustomerProfileService;
import com.lg.JepcoCsPortal.utils.MessageBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
        messageBody.setBody(customerProfileService.findById(customerId));
        messageBody.setKey("success");
        messageBody.setStatus("200");

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/register")
    public ResponseEntity<MessageBody> registerCustomer(@RequestBody CustomerProfile customerProfile) {

        MessageBody messageBody = MessageBody.getInstance();

        boolean fileNumberExists;
        boolean mobileNumberExists;
        boolean nationalExists;
        boolean emailExists;

        fileNumberExists = customerProfileService.findByFileNumber(customerProfile.getFileNumber()) != null;
        mobileNumberExists = customerProfileService.findByMobileNumber(customerProfile.getMobileNumber()) != null;
        nationalExists = customerProfileService.findByNationalNumber(customerProfile.getNationalNumber()) != null;
        emailExists = customerProfileService.findByEmail(customerProfile.getEmail()) != null;

        if (fileNumberExists || mobileNumberExists || nationalExists || emailExists)
        {
            JSONArray resBody = new JSONArray();

            if (fileNumberExists)
                resBody.add("fileNumberExists");

            if (mobileNumberExists)
                resBody.add("mobileNumberExists");

            if (nationalExists)
                resBody.add("nationalExists");

            if (emailExists)
                resBody.add("emailExists");

            messageBody.setBody(resBody.toJSONString());
            messageBody.setKey("failed");
            messageBody.setStatus("400");
        }
        else
        {
            CustomerProfile customerProfile1 = null;

            try {
                customerProfile1 = customerProfileService.save(customerProfile);

                messageBody.setBody(customerProfile1);
                messageBody.setKey("success");
                messageBody.setStatus("200");
            }
            catch (Exception e)
            {
                messageBody.setBody(e.getMessage());
                messageBody.setKey("failed");
                messageBody.setStatus("400");
            }
        }


        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/login")
    public ResponseEntity<MessageBody> loginCustomer(@RequestBody Login login) {

        CustomerProfile customerProfile = customerProfileService.login(login);

        MessageBody messageBody = MessageBody.getInstance();

        if (customerProfile != null)
        {
            messageBody.setBody(customerProfile);
            messageBody.setKey("success");
            messageBody.setStatus("200");
        }
        else
        {
            messageBody.setBody("Wrong email or password");
            messageBody.setKey("failed");
            messageBody.setStatus("404");
        }

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

}
