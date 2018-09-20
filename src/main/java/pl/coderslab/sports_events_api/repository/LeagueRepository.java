package pl.coderslab.sports_events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_events_api.entity.League;

import java.util.List;

public interface LeagueRepository extends JpaRepository <League, Integer> {

}
