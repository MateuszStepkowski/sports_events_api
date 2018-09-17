package pl.coderslab.sports_events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_events_api.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer> {
}
