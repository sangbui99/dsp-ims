package com.admicro.adopt.dspims.model.imp;

import lombok.Data;

import java.util.List;

@Data
public class RequestIMP {
    private String id;
    private  List<IMPModel> imp;
}
