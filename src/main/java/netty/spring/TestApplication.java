package netty.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack","true");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println("success!!!!");
    }
}
