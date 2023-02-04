package com.admicro.adopt.dspims.worker;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerPlay {
    public void update(){
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(3);
        stpe.scheduleWithFixedDelay(new API_SSP(), 0,3, TimeUnit.MINUTES);

    }
}
