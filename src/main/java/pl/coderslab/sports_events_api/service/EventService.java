package pl.coderslab.sports_events_api.service;

import pl.coderslab.sports_events_api.dto.EventDto;
import pl.coderslab.sports_events_api.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    List<Event> findAllInPlay();

    void saveAll(List<Event> events);

    void save(Event event);

    EventDto convert(Event event);
}
