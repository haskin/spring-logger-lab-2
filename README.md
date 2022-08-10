# Spring Logger Lab 2

## Instruction

Define a new logging configuration that does the following:

1. Add a new file appender that outputs log statements to a file named
   `flatiron-spring-trace.log`.
2. Configure this appender with a pattern that follows these rules:
   1. Start each line with the log level, which should always be exactly 3
      characters and truncate from the beginning:
      1. Hint: the modifier to truncate from the beginning is: `.-<num-chars>`
   2. Next, each line should have the thread in the following format:
      `[THREAD:main]`
   3. Next, each line should have the fully qualified class name in full.
   4. Next each line should have a new line character.
   5. Next each line should have two tabs (hint: tab is a special character
      `\t`).
   6. Next each line should have the actual message passed into the logger.
   7. Next each line should have another new line character.
3. Configure your logging so that only classes in the package
   `com.flatiron.spring` use this new appender.

The output in `flatiron-spring-trace.log` should look something like this:

```java
INF [THREAD:main] com.flatiron.spring.FlatironSpring.FlatironSpringApplication
		Starting FlatironSpringApplication using Java 17.0.3 on asterix.lan with PID 31468 (/Users/dbash/sandbox/flatiron/flatiron-spring/build/classes/java/main started by dbash in /Users/dbash/sandbox/flatiron/flatiron-spring)
DEB [THREAD:main] com.flatiron.spring.FlatironSpring.FlatironSpringApplication
		Running with Spring Boot v2.7.1, Spring v5.3.21
INF [THREAD:main] com.flatiron.spring.FlatironSpring.FlatironSpringApplication
		No active profile set, falling back to 1 default profile: "default"
INF [THREAD:main] com.flatiron.spring.FlatironSpring.FlatironSpringApplication
		Started FlatironSpringApplication in 1.037 seconds (JVM running for 1.266)
TRA [THREAD:http-nio-8080-exec-4] com.flatiron.spring.FlatironSpring.HelloController
		Entering hello()
TRA [THREAD:http-nio-8080-exec-4] com.flatiron.spring.FlatironSpring.HelloController
		Fetching joke of the moment
TRA [THREAD:http-nio-8080-exec-4] com.flatiron.spring.FlatironSpring.HelloController
		Returning greeting from hello()
```