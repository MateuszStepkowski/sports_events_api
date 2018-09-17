package pl.coderslab.sports_events_api.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    @ManyToOne
    private League league;

    @NotNull
    @ManyToOne
    private Team teamA;

    @NotNull
    @ManyToOne
    private Team teamB;


    private int teamApts=0;

    private int teamBpts=0;


    private LocalDateTime endDate;


    public Event(LocalDateTime now) {
    }

    public Event(@NotNull LocalDateTime startDate, @NotNull League league, @NotNull Team teamA, @NotNull Team teamB) {
        this.startDate = startDate;
        this.league = league;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public int getTeamApts() {
        return teamApts;
    }

    public void setTeamApts(int teamApts) {
        this.teamApts = teamApts;
    }

    public int getTeamBpts() {
        return teamBpts;
    }

    public void setTeamBpts(int teamBpts) {
        this.teamBpts = teamBpts;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getId() == event.getId() &&
                Objects.equals(getStartDate(), event.getStartDate()) &&
                Objects.equals(getLeague(), event.getLeague()) &&
                Objects.equals(getTeamA(), event.getTeamA()) &&
                Objects.equals(getTeamB(), event.getTeamB());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStartDate(), getLeague(), getTeamA(), getTeamB());
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", league=" + league +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                ", teamApts=" + teamApts +
                ", teamBpts=" + teamBpts +
                ", endDate=" + endDate +
                '}';
    }
}
