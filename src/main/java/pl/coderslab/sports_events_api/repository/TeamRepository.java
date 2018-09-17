package pl.coderslab.sports_events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;

import java.util.List;
import java.util.Set;

public interface TeamRepository extends JpaRepository <Team, Integer> {

    List<Team> findAllByLeaguesContains(League league);
}
