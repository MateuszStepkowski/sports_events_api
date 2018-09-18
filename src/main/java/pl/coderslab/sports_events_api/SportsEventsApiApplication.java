package pl.coderslab.sports_events_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.sports_events_api.service.EventService;
import pl.coderslab.sports_events_api.service.EventsDataSupplierService;

@EnableScheduling
@SpringBootApplication
public class SportsEventsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsEventsApiApplication.class, args);
    }


    @Autowired
    EventsDataSupplierService simulatorService;

    @Autowired
    EventService eventService;

}
