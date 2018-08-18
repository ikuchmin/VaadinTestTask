package com.haulmont.testtask;

import com.haulmont.testtask.models.Role;
import com.haulmont.testtask.models.User;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {
        "com.haulmont.testtask.beans"
})
@EnableJpaRepositories(basePackages = {
        "com.haulmont.testtask.repositories"
})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            RoleRepository roleRepository) {
        return (args) -> {
            Role doctor = new Role("doctor", "crew of the hospital");
            Role patient = new Role("patient", "client of the hospital");
            User upatient = new User(
                    "fntest",
                    "mntest",
                    "sntest",
                    "phone",
                    patient
            );
            User dpatient = new User(
                    "doc",
                    "doc",
                    "doc",
                    "phone123",
                    "Therapist",
                    doctor
            );
            roleRepository.save(doctor);
            roleRepository.save(patient);
            userRepository.save(upatient);
            userRepository.save(upatient);
            userRepository.save(upatient);
            userRepository.save(dpatient);
            userRepository.save(dpatient);
            log.info(roleRepository.findAll().toString());
            log.info(userRepository.findAll().toString());
            log.info("bean initialized");
            log.info("bean initialized2");
        };
    }
}