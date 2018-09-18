package pl.coderslab.sports_events_api.service;


import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;

import java.util.List;

public interface EventsGeneratorService {


    Event generateNewEvent(List<Team> teams, League league);


    Event updateInPlayEvent(Event event);

}
