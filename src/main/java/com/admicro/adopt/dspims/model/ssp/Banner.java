package com.admicro.adopt.dspims.model.ssp;

import lombok.Data;

@Data
public class Banner {

    private String id;
    private String placementId;
    private String campaignId;
    private String revenueType;
    private Integer sspId;
    private String activationDate;
    private String expirationDate;
    private String tittle;
    private Boolean isData;
    private String data;
}
