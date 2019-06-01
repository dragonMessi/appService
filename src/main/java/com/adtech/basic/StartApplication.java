package com.adtech.basic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@SpringBootApplication
//@EnableConfigurationProperties
//@EnableScheduling
//public class StartApplication extends SpringBootServletInitializer {
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(StartApplication.class);
//	}
//
//
//	public static void main(String[] args) {
//
//		SpringApplication.run(StartApplication.class, args);
//	}
//
//}

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class StartApplication {


	public static void main(String[] args) {

		SpringApplication.run(StartApplication.class, args);
	}

}
