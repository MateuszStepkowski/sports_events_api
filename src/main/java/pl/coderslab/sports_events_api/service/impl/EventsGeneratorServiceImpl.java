package pl.coderslab.sports_events_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;
import pl.coderslab.sports_events_api.service.EventService;
import pl.coderslab.sports_events_api.service.EventsGeneratorService;
import pl.coderslab.sports_events_api.service.TeamService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EventsGeneratorServiceImpl implements EventsGeneratorService {

    @Autowired
    TeamService teamService;

    @Autowired
    EventService eventService;

    @Override
    public List<Event> generateNewEvents(League league) {

        Random random = new Random();

        List<Team> teams = teamService.findAllInLeague(league);

        List<Event> events = new ArrayList<>();

        while (teams.size() >= 2) {

            Team teamA = teams.remove(random.nextInt(teams.size())),
                    teamB = teams.remove(random.nextInt(teams.size()));

            LocalDateTime startDate = LocalDateTime.now().plusMinutes(random.nextInt(3) + 1);

            events.add(new Event(startDate, league, teamA, teamB));

        }

        eventService.saveAll(events);

        return events;
    }

    @Override
    public List<Event> updateAllInPlayEvents() {

        updateFootBallEventsState();
        updateBasketBallEventsState();

    }


    public List<Event> updateFootBallEventsState() {

        Random random = new Random();

        List<Event> footballInPlayEvents = eventService.findAllInPlayBySport(LocalDateTime.now(), "football");

        List<Event> updatedEvents = new ArrayList<>();

        for (Event event : footballInPlayEvents) {

            int teamA_power = event.getTeamA().getOffensiveRating() - event.getTeamB().getDeffensiveRating();
            int teamB_power = event.getTeamB().getOffensiveRating() - event.getTeamA().getDeffensiveRating();

            //randomize if there is a goal situation
            if (random.nextInt(100) - (teamA_power + teamB_power) / 2 < 40) {

                //randomize which team has goal situation
                // taking account of both teams offensive & deffensive power
                teamA_power = (event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating()) / 2;
                teamB_power = (event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating()) / 2;

                //if true --> teamA has goal situation, else teamB
                if (random.nextInt(100) - (teamA_power - teamB_power) / 2 < 50) {

                    teamA_power = event.getTeamA().getOffensiveRating();
                    teamB_power = event.getTeamB().getDeffensiveRating();


                    if (random.nextInt(100) - (teamA_power - teamB_power) / 2 < 50) {

                        event.setTeamA_pts(event.getTeamA_pts() + 1);
                        eventService.save(event);
                        updatedEvents.add(event);
                    }

                } else {
                    teamB_power = event.getTeamB().getOffensiveRating();
                    teamA_power = event.getTeamA().getDeffensiveRating();


                    if (random.nextInt(100) - (teamB_power - teamA_power) / 2 < 50) {

                        event.setTeamB_pts(event.getTeamB_pts() + 1);
                        eventService.save(event);
                        updatedEvents.add(event);
                    }
                }
            }


            //game lasts max 3 min
            if (LocalDateTime.now().minusMinutes(3l).isAfter(event.getStartDate())) {

                event.setEndDate(LocalDateTime.now());

                eventService.save(event);
            }

        }

        return updatedEvents;
    }

    public List<Event> updateBasketBallEventsState() {

        Random random = new Random();

        List<Event> basketballInPlayEvents = eventService.findAllInPlayBySport(LocalDateTime.now(), "basketball");

        List<Event> updatedEvents = new ArrayList<>();

        for (Event event : basketballInPlayEvents) {

            int teamA_power = event.getTeamA().getOffensiveRating() - event.getTeamB().getDeffensiveRating();
            int teamB_power = event.getTeamB().getOffensiveRating() - event.getTeamA().getDeffensiveRating();

            //randomize abstract amount of basket situations for each team
            int basket_situations = random.nextInt(15) * (teamA_power + teamB_power) / 20;

            // split basket situations between both teams with a little random factor

            teamA_power = (event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating()) / 2;
            teamB_power = (event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating()) / 2;

            int teamA_situations = Math.toIntExact(Math.round(basket_situations / 2 * teamA_power / teamB_power * (1 + Math.random() / 2)));
            int teamB_situations = Math.toIntExact(Math.round(basket_situations / 2 * teamB_power / teamA_power * (1 + Math.random() / 2)));


            teamA_power = event.getTeamA().getOffensiveRating() - event.getTeamB().getDeffensiveRating();
            teamB_power = event.getTeamB().getOffensiveRating() - event.getTeamA().getDeffensiveRating();


            int teamA_scoredPoints = teamA_situations * (1 + teamA_power / 100) * 2;
            int teamB_scoredPoints = teamB_situations * (1 + teamB_power / 100) * 2;


            event.setTeamA_pts(event.getTeamA_pts() + teamA_scoredPoints);
            event.setTeamB_pts(event.getTeamB_pts() + teamB_scoredPoints);
            eventService.save(event);
            updatedEvents.add(event);


            //game lasts max 3 min
            if (LocalDateTime.now().minusMinutes(3l).isAfter(event.getStartDate())) {

                event.setEndDate(LocalDateTime.now());

                eventService.save(event);
            }

        }




        return updatedEvents;
    }


}

