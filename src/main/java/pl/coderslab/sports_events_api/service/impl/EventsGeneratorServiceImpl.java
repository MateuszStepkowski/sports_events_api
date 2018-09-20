package pl.coderslab.sports_events_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_events_api.entity.Event;
import pl.coderslab.sports_events_api.entity.League;
import pl.coderslab.sports_events_api.entity.Team;
import pl.coderslab.sports_events_api.service.EventService;
import pl.coderslab.sports_events_api.service.EventsGeneratorService;
import pl.coderslab.sports_events_api.service.TeamService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EventsGeneratorServiceImpl implements EventsGeneratorService {


    @Override
    public Event generateNewEvent(List<Team> teams, League league) {

        Random random = new Random();

        if (teams.size() >= 2) {

            Team teamA = teams.remove(random.nextInt(teams.size())),
                    teamB = teams.remove(random.nextInt(teams.size()));
            System.out.println(teamA.getName()+" vs "+teamB.getName()+ " will start in 2 minutes...");
            Timestamp startDate = new Timestamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2));
            return (new Event(startDate, league, teamA, teamB));

        }

        return null;
    }

    @Override
    public Event updateInPlayEvent(Event event) {

        if (event.getLeague().getSport().getName().equals("basketball")) {
            return updateBasketBallEventState(event);
        } else if (event.getLeague().getSport().getName().equals("football")) {
            return updateFootBallEventState(event);
        }

        return null;
    }


    public Event updateFootBallEventState(Event event) {

        Random random = new Random();


        //game lasts max 2 min

        Timestamp now = new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(2));

        if (now.after(event.getStartDate())) {

            event.setEndDate(new Timestamp(System.currentTimeMillis()));


            if (event.getLive_duration_time()<90) event.setLive_duration_time(90);
            event.setEndDate(now);

            soutEventFinalResult(event);

            return event;
        }

        event.setLive_duration_time(event.getLive_duration_time()+random.nextInt(2)+9);

        int teamA_power = event.getTeamA().getOffensiveRating() - event.getTeamB().getDeffensiveRating();
        int teamB_power = event.getTeamB().getOffensiveRating() - event.getTeamA().getDeffensiveRating();


        //randomize if it is close to goal situation
        if (random.nextInt(100) - (teamA_power + teamB_power) / 2 < 30) {

            //randomize which team is close to goal situation
            // taking account of both teams offensive & deffensive power
            teamA_power = (event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating()) / 2;
            teamB_power = (event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating()) / 2;

            //if true --> teamA has goal situation, else teamB
            if (random.nextInt(100) - (teamA_power - teamB_power) / 2 < 50) {

                System.out.println("\ntime: "+event.getLive_duration_time()+"'"+
                        "event id: "+event.getId()+"--> "+event.getTeamA().getName()+" has a goal situation");

                teamA_power = event.getTeamA().getOffensiveRating();
                teamB_power = event.getTeamB().getDeffensiveRating();


                if (random.nextInt(100) - (teamA_power - teamB_power) / 2 < 50) {

                    System.out.println("\ntime: "+event.getLive_duration_time()+"'"+
                            "event id: "+event.getId()+"--> "+event.getTeamA().getName()+" scored !!!");


                    event.setTeamA_pts(event.getTeamA_pts() + 1);

                    return event;
                }

            } else {

                System.out.println("\ntime: "+event.getLive_duration_time()+"'"+
                        "event id: "+event.getId()+"--> "+event.getTeamB().getName()+" has a goal situation");

                teamB_power = event.getTeamB().getOffensiveRating();
                teamA_power = event.getTeamA().getDeffensiveRating();


                if (random.nextInt(100) - (teamB_power - teamA_power) / 2 < 50) {

                    System.out.println("\ntime: "+event.getLive_duration_time()+"'"+
                            "event id: "+event.getId()+"--> "+event.getTeamB().getName()+" scored !!!");

                    event.setTeamB_pts(event.getTeamB_pts() + 1);

                    return event;
                }
            }
        }

        return event;
    }


    public Event updateBasketBallEventState(Event event) {

        Random random = new Random();


        //game lasts max 2 min
        Timestamp now = new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(2));
        if (now.after(event.getStartDate())) {

            event.setLive_duration_time(40);
            event.setEndDate(new Timestamp(System.currentTimeMillis()));

            soutEventFinalResult(event);

            return event;

        }


        event.setLive_duration_time(event.getLive_duration_time()+random.nextInt(2)+3);
        if (event.getLive_duration_time()>40) event.setLive_duration_time(40);

        int teamA_power = event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating();
        int teamB_power = event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating();

        //randomize abstract amount of basket situations for each team
        int basket_situations = random.nextInt(12) + ( (teamA_power + teamB_power) / 100 );

        // split basket situations between both teams with a little random factor

        teamA_power = teamA_power / 2;
        teamB_power = teamB_power / 2;

        int teamA_situations = Math.toIntExact(Math.round(basket_situations / 2 * teamA_power / teamB_power * (1 + Math.random() / 2)));
        int teamB_situations = Math.toIntExact(Math.round(basket_situations / 2 * teamB_power / teamA_power * (1 + Math.random() / 2)));


        teamA_power = event.getTeamA().getOffensiveRating() - event.getTeamB().getDeffensiveRating();
        teamB_power = event.getTeamB().getOffensiveRating() - event.getTeamA().getDeffensiveRating();


        int teamA_scoredPoints = teamA_situations * (1 + teamA_power / 100) * 2 * (random.nextInt(6)+5)/8;
        int teamB_scoredPoints = teamB_situations * (1 + teamB_power / 100) * 2 * (random.nextInt(6)+5)/8;


        event.setTeamA_pts(event.getTeamA_pts() + teamA_scoredPoints);
        event.setTeamB_pts(event.getTeamB_pts() + teamB_scoredPoints);

        System.out.println("\ntime: "+event.getLive_duration_time()+"':"+
                "event id:"+event.getId()+"-->"+
                event.getTeamA().getName()+" |"+event.getTeamA_pts()+":"+
                event.getTeamB_pts()+"| "+event.getTeamB().getName());


        return event;
}


    private static void soutEventFinalResult(Event event) {
        if (event.getTeamA_pts() > event.getTeamB_pts()){
            System.out.println("event id: "+event.getId()+"--> "+event.getTeamA().getName()+" Won");
            event.getTeamB().setLooses(event.getTeamB().getLooses()+1);
            event.getTeamA().setWins(event.getTeamA().getWins()+1);
        }else if (event.getTeamA_pts() < event.getTeamB_pts()){
            System.out.println("event id: "+event.getId()+"--> "+event.getTeamB().getName()+" Won");
            event.getTeamA().setLooses(event.getTeamA().getLooses()+1);
            event.getTeamB().setWins(event.getTeamB().getWins()+1);
        }else {
            System.out.println("event id: "+event.getId()+"--> Draw");
            event.getTeamA().setDraws(event.getTeamA().getDraws()+1);
            event.getTeamB().setDraws(event.getTeamB().getDraws()+1);

        }
}
}

