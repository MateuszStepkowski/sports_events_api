package pl.coderslab.sports_events_api.service;

import pl.coderslab.sports_events_api.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    List<Event> findAllInPlayBySport(LocalDateTime startDate, String sportName);

    void saveAll(List<Event> events);

    void save(Event event);
}
