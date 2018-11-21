package com.lg.JepcoCsPortal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("ok");
        return "ok";
    }

    //    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @PostMapping("/upload")
    public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            FileOutputStream fos = new FileOutputStream(
                    "D:/ionic project/"+file.getOriginalFilename());
            System.out.println(">> "+file.getOriginalFilename());
            try {
                fos.write(bytes);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fos.close();
            }
            return "works";
        } else {
            return "doesn't work";
        }
    }
}
