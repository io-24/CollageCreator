package collage.controller;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.ExponentialBackOff;

import java.io.IOException;
import java.util.logging.Logger;

public class CustomBackOff implements BackOff {

    final private ExponentialBackOff backoff;

    public CustomBackOff(){
        backoff = new ExponentialBackOff.Builder().setInitialIntervalMillis(500)
                .setMaxElapsedTimeMillis(6000).setMaxIntervalMillis(3000).setMultiplier(1.5)
                .setRandomizationFactor(0.5).build();
    }

    @Override
    public long nextBackOffMillis() throws IOException {
        long nexTime = backoff.nextBackOffMillis();
        Logger.getGlobal().info("call nextBackOffMillis: " + nexTime);
       return nexTime;
    }


    @Override
    public void reset() throws IOException {
        Logger.getGlobal().info("call reset");
        backoff.reset();
    }
}