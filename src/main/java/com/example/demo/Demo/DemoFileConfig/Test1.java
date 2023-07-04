package com.example.demo.Demo.DemoFileConfig;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "app12")
//@PropertySource("classpath:application-dev.properties")
public class Test1 {
   // @Value("${app12.name}")
    String name;
   // @Value("${app12.version}")
    String version;

  public Test1() {
    System.out.println(name);
    System.out.println(version);
  }
}
