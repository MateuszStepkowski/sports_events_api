package pl.coderslab.sports_events_api.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EventDto {

    @NotNull
    private LocalDateTime startDate;

    @NotBlank
    private String sport;

    @NotBlank
    private String country;

    @NotBlank
    private String league;

    @NotBlank
    private String teamA;

    @NotBlank
    private String teamB;


    private int teamA_pts=0;

    private int teamB_pts=0;


    private LocalDateTime endDate;


    public EventDto(@NotNull LocalDateTime startDate, @NotBlank String sport, @NotBlank String country,
                    @NotBlank String league, @NotBlank String teamA, @NotBlank String teamB,
                    int teamA_pts, int teamB_pts, LocalDateTime endDate) {
        this.startDate = startDate;
        this.sport = sport;
        this.country = country;
        this.league = league;
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamA_pts = teamA_pts;
        this.teamB_pts = teamB_pts;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
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

    public int getTeamA_pts() {
        return teamA_pts;
    }

    public void setTeamA_pts(int teamA_pts) {
        this.teamA_pts = teamA_pts;
    }

    public int getTeamB_pts() {
        return teamB_pts;
    }

    public void setTeamB_pts(int teamB_pts) {
        this.teamB_pts = teamB_pts;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "startDate=" + startDate +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", sport='" + sport + '\'' +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", teamA_pts=" + teamA_pts +
                ", teamB_pts=" + teamB_pts +
                ", endDate=" + endDate +
                '}';
    }
}
