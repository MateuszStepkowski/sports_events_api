package pl.coderslab.sports_events_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.repository.EventRepository;
import pl.coderslab.sports_events_api.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllInPlayBySport(LocalDateTime startDate, String sportName) {
        return eventRepository.findAllByStartDateAfterAndEndDateIsNullAndLeagueSport_Name(startDate, sportName);
    }

    @Override
    public void saveAll(List<Event> events) {

        eventRepository.saveAll(events);
    }

    @Override
    public void save(Event event) {

        eventRepository.saveAndFlush(event);
    }
}
