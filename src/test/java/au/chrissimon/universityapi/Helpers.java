package au.chrissimon.universityapi;

import java.time.Duration;

import org.springframework.test.web.reactive.server.WebTestClient;

public class Helpers {
    public static String baseUri(long port) { return "http://localhost:" + port; }

	public static WebTestClient newWebClient(long port) {
		return WebTestClient
			.bindToServer()
				.baseUrl(baseUri(port))
				.responseTimeout(Duration.ofSeconds(90))
				.build();
	}
}
