package pl.coderslab.sports_events_api.dto;


import java.util.Date;

public class EventDto {

    private Date startDate;

    private String league;

    private String teamA;

    private String teamB;

    private int teamApoints=0;

    private int teamBpoints=0;

    private Date endDate;


    public EventDto(Date startDate, String league, String teamA, String teamB) {
        this.startDate = startDate;
        this.league = league;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getTeamApoints() {
        return teamApoints;
    }

    public void setTeamApoints(int teamApoints) {
        this.teamApoints = teamApoints;
    }

    public int getTeamBpoints() {
        return teamBpoints;
    }

    public void setTeamBpoints(int teamBpoints) {
        this.teamBpoints = teamBpoints;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
