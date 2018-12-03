package com.lg.JepcoCsPortal.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lg.JepcoCsPortal.entities.CustomerProfile;
import com.lg.JepcoCsPortal.helpers.Login;
import com.lg.JepcoCsPortal.services.CustomerProfileService;
import com.lg.JepcoCsPortal.utils.MessageBody;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping("/customerProfile/findAll")
    public ResponseEntity<MessageBody> findAllCustomers() {

        List<CustomerProfile> customerProfiles = customerProfileService.findAll();
        MessageBody messageBody = MessageBody.getInstance();

        if (!customerProfiles.isEmpty()) {
            messageBody.setBody(customerProfiles);
            messageBody.setKey("success");
            messageBody.setStatus(HttpStatus.OK.value());
        } else {
            messageBody.setBody("No customers found");
            messageBody.setKey("failed");
            messageBody.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @GetMapping("/customerProfile/find/{customerId}")
    public ResponseEntity<MessageBody> findCustomerById(@PathVariable("customerId") Long customerId) {

        MessageBody messageBody = MessageBody.getInstance();

        if (customerId == null) {
            messageBody.setBody("customer id is null");
            messageBody.setKey("failed");
            messageBody.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            Optional<CustomerProfile> customerProfile = customerProfileService.findById(customerId);

            if (customerProfile.isPresent()) {
                messageBody.setBody(customerProfile);
                messageBody.setKey("success");
                messageBody.setStatus(HttpStatus.OK.value());
            } else {
                messageBody.setBody("No customers found");
                messageBody.setKey("failed");
                messageBody.setStatus(HttpStatus.NOT_FOUND.value());
            }
        }

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/register")
    public ResponseEntity<MessageBody> registerCustomer(@RequestBody CustomerProfile customerProfile) {

        MessageBody messageBody = MessageBody.getInstance();

        boolean fileNumberExists;
        boolean mobileNumberExists;
        boolean nationalExists;
        boolean emailExists;

        String mobNum = customerProfile.getMobileNumber();

        if (!mobNum.startsWith("+962")) {
            if (mobNum.startsWith("962")) {
                mobNum = "+" + mobNum;
            } else {
                if (mobNum.startsWith("0")) {
                    mobNum = mobNum.replaceFirst("0", "+962");
                } else {
                    mobNum = "+962" + mobNum;
                }
            }

        }

        customerProfile.setMobileNumber(mobNum);
        System.out.println(customerProfile.getMobileNumber());

        fileNumberExists = customerProfileService.findByFileNumber(customerProfile.getFileNumber()) != null;
        mobileNumberExists = customerProfileService.findByMobileNumber(customerProfile.getMobileNumber()) != null;
        nationalExists = customerProfileService.findByNationalNumber(customerProfile.getNationalNumber()) != null;
        emailExists = customerProfileService.findByEmail(customerProfile.getEmail()) != null;

        if (fileNumberExists || mobileNumberExists || nationalExists || emailExists) {
            JSONArray resBody = new JSONArray();

            if (fileNumberExists)
                resBody.put("fileNumberExists");

            if (mobileNumberExists)
                resBody.put("mobileNumberExists");

            if (nationalExists)
                resBody.put("nationalExists");

            if (emailExists)
                resBody.put("emailExists");

            messageBody.setBody(resBody.toString());
            messageBody.setKey("failed");
            messageBody.setStatus(HttpStatus.FOUND.value());
        } else {
            CustomerProfile customerProfile1 = null;

            try {
                customerProfile1 = customerProfileService.save(customerProfile);

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");

                JSONObject bodyObject = new JSONObject();
                bodyObject.put("firstName", customerProfile.getFirstName());
                bodyObject.put("lastName", customerProfile.getLastName());
                bodyObject.put("email", customerProfile.getEmail());
                bodyObject.put("nationalNumber", customerProfile.getNationalNumber());
                bodyObject.put("fileNumber", customerProfile.getFileNumber());
                bodyObject.put("mobileNumber", customerProfile.getMobileNumber());

                com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType,
                        bodyObject.toString());

                Request request = new Request.Builder()
                        .url("http://217.144.0.210:8085/JepcoMobApiProd/profile/create")
                        .post(body)
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = client.newCall(request).execute();

                String resBody = response.body().string();

                System.out.println("----------------------------\n" + resBody + "\n-------------------------------");

                if (response.code() != 200) {
                    customerProfileService.delete(customerProfile1);

                    JSONObject resObj = null;
                    try {
                        resObj = new JSONObject(resBody);
                    } catch (Exception e) {

                        messageBody.setBody("internal error");

                    }

                    if (resObj != null) {
                        messageBody.setBody(resObj.get("key"));
                    }
                    messageBody.setKey("failed");
                    messageBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                } else {
                    messageBody.setBody(customerProfile1);
                    messageBody.setKey("success");
                    messageBody.setStatus(HttpStatus.OK.value());
                }

            } catch (Exception e) {
                e.printStackTrace();
                customerProfileService.delete(customerProfile1);

                messageBody.setBody(e.getMessage());
                messageBody.setKey("failed");
                messageBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }


        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @PostMapping("/customerProfile/login")
    public ResponseEntity<MessageBody> loginCustomer(@RequestBody Login login) {

        CustomerProfile customerProfile = customerProfileService.login(login);

        MessageBody messageBody = MessageBody.getInstance();

        if (customerProfile != null) {
            messageBody.setBody(customerProfile);
            messageBody.setKey("success");
            messageBody.setStatus(HttpStatus.OK.value());
        } else {
            messageBody.setBody("Wrong email or password");
            messageBody.setKey("failed");
            messageBody.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

}
