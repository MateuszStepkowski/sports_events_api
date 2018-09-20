package pl.coderslab.sports_events_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.dto.EventDto;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.repository.EventRepository;
import pl.coderslab.sports_events_api.service.EventService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllInPlay() {
        return eventRepository.findAllByStartDateBeforeAndEndDateIsNull(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void saveAll(List<Event> events) {

        eventRepository.saveAll(events);
    }

    @Override
    public void save(Event event) {

        eventRepository.saveAndFlush(event);
    }

    @Override
    public EventDto convertToDto(Event event) {

        EventDto eventDto = new EventDto(
                event.getStartDate(),event.getLeague().getSport().getName(),
                event.getLeague().getCountry().getName(), event.getLeague().getName(),
                event.getTeamA().getName(), event.getTeamB().getName(),
                event.getTeamA_pts(), event.getTeamB_pts(), event.getLive_duration_time());

        return eventDto;
    }
}
