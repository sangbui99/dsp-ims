package com.admicro.adopt.dspims.service.impl;

import com.admicro.adopt.dspims.cache.DataCache;
import com.admicro.adopt.dspims.constants.SSPConstants;
import com.admicro.adopt.dspims.model.imp.IMPModel;
import com.admicro.adopt.dspims.model.imp.RequestIMP;
import com.admicro.adopt.dspims.model.ssp.Banner;
import com.admicro.adopt.dspims.model.ssp.ZoneCP;
import com.admicro.adopt.dspims.service.IIMPService;
import dsp.models.Bid;
import dsp.models.SeatBid;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Component
public class IMPService implements IIMPService {


    @Override
    public SeatBid zoneBanner(RequestIMP requestIMP, HttpServletRequest httpServletRequest) {
//        List<IMPModel> impModels = requestIMP.getIMPModels().stream().collect(Collectors.toList());

        SeatBid seatBid = new SeatBid();
        for (IMPModel impModel : requestIMP.getImp()) {
            if (!DataCache.ZoneCache.containsKey(impModel.getId()))
                continue;

            ZoneCP zoneCP = DataCache.ZoneCache.get(impModel.getId());

            if (zoneCP.getBanners().get(0).getIsData()) {
                Bid bid = new Bid();
                bid.setImpid(impModel.getId() + "");
                bid.setId(String.valueOf(UUID.randomUUID()));
                bid.setPrice(100f);
                String dataTemp = SSPConstants.TEMPLATE.replace("<:SSPID:>", impModel.getId().toString());

                dataTemp = dataTemp.replace("<:ZONEID:>", zoneCP.getZoneCPId());

                dataTemp = dataTemp.replace("<:PRODUCT_NAME:>", "admicro_adsmanager");
                String zoneSize = zoneCP.getWidth() + "x" + zoneCP.getHeight();
                dataTemp = dataTemp.replace("<:ZONE_SIZE:>", zoneSize);
                dataTemp = dataTemp.replace("<:DATA:>", "[" + zoneCP.getBanners().get(0).getData() + "]");

                bid.setAdm(dataTemp);
//            "http://".concat(httpServletRequest.getServerName().concat(":")).concat(String.valueOf(httpServletRequest.getServerPort()).concat(FilePathConstants.pathFile))
                bid.setNurl(httpServletRequest.getRequestURL() + " rid=" + requestIMP.getId() + "&price=${AUCTION_PRICE}");
                if (zoneCP.getBanners().size() > 0 && zoneCP.getBanners().get(0).getId() != null) {
                    bid.setAdid(zoneCP.getBanners().get(0).getId());
                }
                bid.getExt().put("click_through_url", "");
                seatBid.addBid(bid);
            } else {
                Bid bid = null;
                seatBid.addBid(bid);
            }

        }
        return seatBid;
    }
}
