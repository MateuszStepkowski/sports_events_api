package pl.coderslab.sports_events_api.service;


import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;

import java.util.List;

public interface EventsService {


    List<Event> generateNewEvents(League league);


    void updateEventState(Event event);

}
