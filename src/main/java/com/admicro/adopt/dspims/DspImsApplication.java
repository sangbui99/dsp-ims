package com.admicro.adopt.dspims;

import com.admicro.adopt.dspims.worker.WorkerPlay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DspImsApplication {

    public static void main(String[] args) {

        SpringApplication.run(DspImsApplication.class, args);

        WorkerPlay workerPlay = new WorkerPlay();
        workerPlay.update();
    }

}
