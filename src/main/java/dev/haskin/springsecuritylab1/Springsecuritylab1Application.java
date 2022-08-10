package dev.haskin.springsecuritylab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * Problems:
 	- Add a new file appender that outputs log statements to a file named flatiron-spring-trace.log.
	- Configure this appender with a pattern that follows these rules:
			- Start each line with the log level, which should always be exactly 3 characters and truncate from the beginning:
				Hint: the modifier to truncate from the beginning is: .-<num-chars>
			- Next, each line should have the thread in the following format: [THREAD:main]
			- Next, each line should have the fully qualified class name in full.
			- Next each line should have a new line character.
			- Next each line should have two tabs (hint: tab is a special character \t).
			- Next each line should have the actual message passed into the logger.
			- Next each line should have another new line character.
 */
@SpringBootApplication
public class Springsecuritylab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecuritylab1Application.class, args);
	}

	@Bean
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
