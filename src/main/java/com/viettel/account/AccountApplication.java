package com.viettel.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class AccountApplication {
    private static final Logger log = LoggerFactory.getLogger(AccountApplication.class);

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(AccountApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        ConfigurableEnvironment environment = context.getEnvironment();

        log.info("\n\n\t\t----------------------------------------------------------------------------------\n" +
                        "\t\t\t\t\tApplication {} is running on port {}" +
                        "\n\t\t\t\t\tAccess: http://localhost:{}/sw" +
                        "\n\t\t----------------------------------------------------------------------------------\n",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                environment.getProperty("server.port")
        );
    }
}
