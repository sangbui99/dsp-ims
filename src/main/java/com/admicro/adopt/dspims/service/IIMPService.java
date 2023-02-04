package com.admicro.adopt.dspims.service;

import com.admicro.adopt.dspims.model.imp.RequestIMP;
import dsp.models.SeatBid;

import javax.servlet.http.HttpServletRequest;

public interface IIMPService {
     SeatBid zoneBanner(RequestIMP requestIMP, HttpServletRequest httpServletRequest);
}
