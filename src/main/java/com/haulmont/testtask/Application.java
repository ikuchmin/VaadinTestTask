package com.haulmont.testtask;

import com.haulmont.testtask.UI.views.MainView;
import com.haulmont.testtask.UI.components.HeaderComponent;
import com.haulmont.testtask.UI.models.RouteLink;
import com.haulmont.testtask.UI.views.DoctorsView;
import com.haulmont.testtask.UI.views.ErrorView;
import com.haulmont.testtask.UI.views.PatientsView;
import com.haulmont.testtask.UI.views.RecipesView;
import com.haulmont.testtask.models.Role;
import com.haulmont.testtask.models.User;
import com.haulmont.testtask.repositories.RoleRepository;
import com.haulmont.testtask.repositories.UserRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		"com.haulmont.testtask.beans",
		"com.haulmont.testtask.atest",
		"com.haulmont.testtask.atestbean",
		"com.haulmont.testtask.UI.views"
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

        @Autowired
	    private MainView mainView;
        @Autowired
	    private DoctorsView doctorsView;
        @Autowired
	    private PatientsView patientsView;
        @Autowired
	    private RecipesView recipesView;

        private List<RouteLink> links;
        private Navigator navigator;

        private VerticalLayout mainLayout = new VerticalLayout();
        private Panel header = new Panel();
        private Panel container = new Panel();

        @Override
        protected void init(VaadinRequest vaadinRequest) {
            links = getLinks();
            navigator = getNavigator(links, new ErrorView(), container);
            header.setContent(new HeaderComponent(links, navigator));
            mainLayout.addComponent(header);
            mainLayout.addComponent(container);
            this.setContent(mainLayout);
        }

        @Autowired
        private List<RouteLink> getLinks() {
            List<RouteLink> links = new ArrayList<>();
            links.add(new RouteLink("", "Main", mainView));
            links.add(new RouteLink("doctors", "Doctors", doctorsView));
            links.add(new RouteLink("patients", "Patients", patientsView));
            links.add(new RouteLink("recipes", "Recipes", recipesView));
            return links;
        }

        private Navigator getNavigator(List<RouteLink> views, View errorView, SingleComponentContainer container) {
            Navigator navigator = new Navigator(this, container);
            for (RouteLink link : views) {
                navigator.addView(link.getUrl(), link.getComponent());
            }
            navigator.setErrorView(errorView);
            return navigator;
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
            User udoctor = new User(
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
            userRepository.save(udoctor);
            System.out.println(userRepository.getAllUsersWithRole("doctor"));
            log.info(roleRepository.findAll().toString());
            log.info(userRepository.findAll().toString());
            log.info("bean initialized");
            log.info("bean initialized2");
        };
    }
}
