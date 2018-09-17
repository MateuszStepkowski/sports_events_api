package pl.coderslab.sports_events_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.repository.LeagueRepository;
import pl.coderslab.sports_events_api.service.EventService;
import pl.coderslab.sports_events_api.service.EventsGeneratorService;
import pl.coderslab.sports_events_api.service.EventsSimulatorService;
import pl.coderslab.sports_events_api.service.LeagueService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsSimulatorServiceImpl implements EventsSimulatorService {

    @Autowired
    EventsGeneratorService generatorService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    EventService eventService;


    @Override
    @Scheduled(fixedRate = 6 * 60 * 100, initialDelay = 2 * 60 * 1000)
    public void generateNew() {

        List<League> leagues = leagueService.findAll();
        List<Event> generatedEvents = new ArrayList<>();

        for (League league : leagues) {

            generatedEvents.addAll(generatorService.generateNewEvents(league));
        }

        //TO DO ---> http Clients post data sending


        eventService.saveAll(generatedEvents);
    }

    @Override
    @Scheduled(fixedRate = 15 * 1000, initialDelay = 3 * 60 * 1000)
    public void simulateGenerated() {

        List<Event> updatedEvents = generatorService.updateAllInPlayEvents();

        //TO DO ---> http Clients post data sending

        eventService.saveAll(updatedEvents);
    }
}
