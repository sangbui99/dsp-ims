package com.admicro.adopt.dspims.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WinnoticeController {
    private static byte[] trackingGif = { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61, 0x1, 0x0, 0x1, 0x0, (byte) 0x80, 0x0, 0x0,
            (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x0, 0x0, 0x0, 0x2c, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0, 0x0,
            0x2, 0x2, 0x44, 0x1, 0x0, 0x3b };

    @GetMapping("/winnotice")
    public ResponseEntity<byte[]> WinN(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return ResponseEntity.ok()
                .headers(headers)
                .body(trackingGif);
    }
}
