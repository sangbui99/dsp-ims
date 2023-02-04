package com.admicro.adopt.dspims.controller;

import com.admicro.adopt.dspims.model.imp.RequestIMP;
import com.admicro.adopt.dspims.service.IIMPService;
import dsp.models.SeatBid;
import dsp.models.response.BidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IMPController {
    @Autowired
    IIMPService impService;

    @PostMapping("/dsp-imp")
    public ResponseEntity<BidResponse> zoneBanner(@RequestBody RequestIMP requestIMP, HttpServletRequest httpServletRequest){
        BidResponse bidResponse = new BidResponse();
        SeatBid seatBid;
        try {
            seatBid = impService.zoneBanner(requestIMP, httpServletRequest);
            if(seatBid.getBid().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bidResponse);
            }
            bidResponse.setId(requestIMP.getId());
            bidResponse.addSeatbid(seatBid);
            bidResponse.setCur("USD");
                return ResponseEntity.status(HttpStatus.OK).body(bidResponse);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bidResponse);
        }
    }
}
