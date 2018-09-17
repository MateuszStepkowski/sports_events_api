package pl.coderslab.sports_events_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.repository.LeagueRepository;

import java.util.List;

public interface LeagueService {

    List<League> findAll();
}
