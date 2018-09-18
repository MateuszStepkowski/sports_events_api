package pl.coderslab.sports_events_api.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.dto.EventDto;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;
import pl.coderslab.sports_events_api.jms.JmsProducer;
import pl.coderslab.sports_events_api.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventsDataSupplierServiceImpl implements EventsDataSupplierService {

    @Autowired
    EventsGeneratorService generatorService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    EventService eventService;

    @Autowired
    TeamService teamService;

    @Autowired
    JmsProducer jmsProducer;

    private final String newEventsQueue = "new_events.t";
    private final String eventsUpdateQueue = "events_update.t";


    @Override
    @Scheduled(fixedRate = (5 * 60 * 1000), initialDelay = 5 * 1000)
    public void generateAndSupply() {
        System.out.println("Started generating");
        List<League> leagues = leagueService.findAll();
        List<Event> generatedEvents = new ArrayList<>();

        for (League league : leagues) {

            List<Team> teams = teamService.findAllInLeague(league);

            while (teams.size() > 1) {

                Event event = generatorService.generateNewEvent(teams, league);
                generatedEvents.add(event);

                //supplying data by JMS
                JSONObject eventJson = new JSONObject(eventService.convertToDto(event));
                jmsProducer.send(eventJson.toString(), newEventsQueue);

            }
        }


        eventService.saveAll(generatedEvents);
    }

    @Override
    @Scheduled(fixedRate = (12 * 1000), initialDelay = (2 * 60 * 1000 + 1000))
    public void simulateGeneratedAndSupply() {

        List<Event> inPlayEvents = eventService.findAllInPlay();

        List<Event> updatedEvents = new ArrayList<>();

        for (Event event : inPlayEvents) {

            Event simulatedEvent = generatorService.updateInPlayEvent(event);

            if (simulatedEvent != null) {

                updatedEvents.add(simulatedEvent);

                //supplying data by JMS
                JSONObject eventJson = new JSONObject(eventService.convertToDto(simulatedEvent));
                jmsProducer.send(eventJson.toString(), eventsUpdateQueue);
            }
        }


        eventService.saveAll(updatedEvents);
    }
}
