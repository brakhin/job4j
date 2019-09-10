package ru.bgbrakhi.spring.xml.beanex.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ContextConfiguration {
    @Bean
    Object1 getObject1() {
        return new Object1();
    }

    @Bean
    Object2 getObject2() {
        return new Object2(getObject1());
    }

    @Bean
    Object3 getObject3() {
        return new Object3(getObject2());
    }
}
