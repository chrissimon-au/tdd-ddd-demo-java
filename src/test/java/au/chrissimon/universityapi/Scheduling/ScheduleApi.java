package au.chrissimon.universityapi.Scheduling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import au.chrissimon.universityapi.Helpers;

@Lazy // To ensure local.server.port is set by the time it is wired.
@Component
public class ScheduleApi {

    @Value(value="${local.server.port}")
    private int port;

    ResponseSpec schedule() {
        return Helpers.newWebClient(port)
            .post()
                .uri("/schedules")
            .exchange();
    }
}
