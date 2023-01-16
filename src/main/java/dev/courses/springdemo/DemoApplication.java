package dev.courses.springdemo;

import dev.courses.springdemo.AssignmentDirectory.otherones.OutputAggregator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DemoApplication.class, args);
		context.getBean(OutputAggregator.class).printInput("Hello world");

	}

}
