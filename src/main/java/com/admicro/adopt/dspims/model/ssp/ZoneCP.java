package com.admicro.adopt.dspims.model.ssp;

import lombok.Data;

import java.util.List;

@Data
public class ZoneCP {
    private String zoneCPId;
    private Integer adSlotId;
    private Integer width;
    private Integer height;
    private Integer zoneTypeValue;
    private String zoneTypeName;
    private List<SSPId> sspId;
    private List<Banner> banners;

}
