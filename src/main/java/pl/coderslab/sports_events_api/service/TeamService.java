package pl.coderslab.sports_events_api.service;

import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAllInLeague(League league);
}
