package com.example.demo;
import com.example.demo.Demo.DemoBeanScope.ServiceA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context =   SpringApplication.run(App.class, args);

        ServiceA serviceA =context.getBean(ServiceA.class);
       // ((ConfigurableApplicationContext) context).getBeanFactory().destroyBean(serviceA);
    }
}
