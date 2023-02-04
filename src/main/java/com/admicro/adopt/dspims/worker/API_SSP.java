package com.admicro.adopt.dspims.worker;

import com.admicro.adopt.dspims.cache.DataCache;
import com.admicro.adopt.dspims.constants.SSPConstants;
import com.admicro.adopt.dspims.model.ssp.ArrData;
import com.admicro.adopt.dspims.model.ssp.ZoneCP;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.HashMap;
import java.util.Map;

public class API_SSP implements Runnable {

    @Override
    public void run() {
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(SSPConstants.URL)
                    .header("authorization", SSPConstants.AUTHORIZATION)
                    .header("Cookie", "lang=en-US")
                    .body("")
                    .asString();

            Gson gson = new Gson();
            ArrData arrData = gson.fromJson(response.getBody(), ArrData.class);
            DataCache.ZoneCache = new HashMap<>();
            for (ZoneCP z : arrData.getArrData()) {
                if (z.getSspId().size() > 0){
                    DataCache.ZoneCache.put(z.getSspId().get(0).getSspId(),z);

                }
            }

//            for(Map.Entry<Integer, ZoneCP> entry: DataCache.ZoneCache.entrySet()){
//                System.out.println();
//                System.out.println(entry.getKey() );
//                System.out.println(entry.getValue().getBanners().get(0).getData());
//
//            }
            System.out.println("Load Cache Done");
        } catch (Exception e) {
            System.err.println("error !");
            e.printStackTrace();
        }
    }
}
