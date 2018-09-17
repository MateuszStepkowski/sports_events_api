package pl.coderslab.sports_events_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.repository.LeagueRepository;
import pl.coderslab.sports_events_api.service.LeagueService;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }
}
