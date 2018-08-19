package com.haulmont.testtask;

import com.haulmont.testtask.UI.models.RouteLink;
import com.haulmont.testtask.UI.views.DoctorsView;
import com.haulmont.testtask.UI.views.MainView;
import com.haulmont.testtask.UI.views.PatientsView;
import com.haulmont.testtask.UI.views.RecipesView;
import com.haulmont.testtask.models.Role;
import com.haulmont.testtask.models.User;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.haulmont.testtask.beans"
})
@EnableJpaRepositories(basePackages = {
		"com.haulmont.testtask.repositories"
})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Theme("valo")
    @SpringUI(path = "")
    public static class MainNavigator extends UI {

        Navigator navigator;

        @Override
        protected void init(VaadinRequest vaadinRequest) {
            navigator = new Navigator(this, this);
            List<RouteLink> links = getLinks();
            for (RouteLink link : links) {
                navigator.addView(link.getUrl(), link.getComponent());
            }
        }

        private List<RouteLink> getLinks() {
            List<RouteLink> links = new ArrayList<>();
            links.add(new RouteLink("", "Main", new MainView(links)));
            links.add(new RouteLink("doctors", "Doctors", new DoctorsView()));
            links.add(new RouteLink("recipes", "Recipes", new RecipesView()));
            links.add(new RouteLink("patients", "Patients", new PatientsView()));
            return links;
        }

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
            log.error(roleRepository.findAll().toString());
            log.error(userRepository.findAll().toString());
            log.error("bean initialized");
            log.error("bean initialized2");
        };
    }
}
