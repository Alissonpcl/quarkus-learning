package io.alissonlima.com.quarkus.orm.panache;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@QuarkusMain
public class AppMain implements QuarkusApplication {

    @Inject
    Logger log;

    @Override
    public int run(String... args) throws Exception {
        MDC.put("marker", UUID.randomUUID().toString());

        Instant startTime = Instant.now();
        log.infof("Starting application at %s", startTime);

        new Thread(() -> log.info("Log from within child thread")).start();

        log.info("Log with marker");

        Instant endTime = Instant.now();
        log.infof("Finishing application after %dms", Duration.between(startTime, endTime).toMillis());


        return 0;
    }

}