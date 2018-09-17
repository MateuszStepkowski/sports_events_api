package pl.coderslab.sports_events_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;
import pl.coderslab.sports_events_api.repository.TeamRepository;
import pl.coderslab.sports_events_api.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Team> findAllInLeague(League league) {
        return teamRepository.findAllByLeaguesContains(league);
    }
}
