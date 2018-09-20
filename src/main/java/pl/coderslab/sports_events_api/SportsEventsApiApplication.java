package pl.coderslab.sports_events_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.sports_events_api.service.EventService;
import pl.coderslab.sports_events_api.service.EventsDataSupplierService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

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



    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }

}
