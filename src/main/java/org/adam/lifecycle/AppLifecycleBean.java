package org.adam.lifecycle;

import org.adam.service.RedisService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");
    @Inject
    RedisService redisService;

    private void initRedisInDevMode() {
        String[] countryCodes = {"sg", "hk"};
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String mockDriverDetails = new Scanner(
                Objects.requireNonNull(AppLifecycleBean.class.getClassLoader().getResourceAsStream("driver_details")), StandardCharsets.UTF_8)
                .useDelimiter("\\A")
                .next();

        for (String countryCode : countryCodes
        ) {
            redisService
                    .zadd(
                            String.format("%s_db:driver_details:123:%s", countryCode, dateFormat.format(currentDate)),
                            1.0,
                            mockDriverDetails
                    );
        }

    }

    void onStart(@Observes StartupEvent ev) {
        switch (io.quarkus.runtime.LaunchMode.current()) {
            case DEVELOPMENT, TEST -> initRedisInDevMode();
        }
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}