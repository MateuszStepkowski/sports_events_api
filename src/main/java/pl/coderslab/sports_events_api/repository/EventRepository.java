package pl.coderslab.sports_events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_events_api.entity.Event;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository <Event, Integer> {

    List<Event> findAllByStartDateBeforeAndEndDateIsNull(Timestamp startDate);


}
