package au.chrissimon.universityapi.Scheduling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchedulingTests {

    @Autowired
    ScheduleApi scheduleApi;

    @Test
    public void givenIAmAnAdmin_WhenIScheduleCourses() {
        ResponseSpec response = scheduleApi.schedule();

        itShouldScheduleTheCourses(response);
    }

    private void itShouldScheduleTheCourses(ResponseSpec response) {
        response
            .expectStatus()
            .isOk();
    }

}
