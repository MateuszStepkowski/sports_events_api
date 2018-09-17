package pl.coderslab.sports_events_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;
import pl.coderslab.sports_events_api.repository.TeamRepository;
import pl.coderslab.sports_events_api.service.EventsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Event> generateNewEvents(League league) {

        Random random = new Random();

        List<Team> teams = teamRepository.findAllByLeaguesContains(league);

        List<Event> events = new ArrayList<>();

        while (teams.size() >= 2) {

            Team  teamA = teams.remove(random.nextInt(teams.size())),
                  teamB = teams.remove(random.nextInt(teams.size()));

            events.add(new Event(LocalDateTime.now(), league, teamA, teamB));

        }

        return events;
    }

    @Override
    public void updateEventState(Event event) {


    }
}
