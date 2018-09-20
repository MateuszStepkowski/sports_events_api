package pl.coderslab.sports_events_api.dto;


import pl.coderslab.sports_events_api.dto.enums.DataTypeEnum;
import pl.coderslab.sports_events_api.dto.enums.EventIsEndedEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

public class EventDto {

    @NotNull
    private Timestamp startDate;

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

    @NotBlank
    private DataTypeEnum dataType;


    private int teamA_pts=0;

    private int teamB_pts=0;

    private int live_duration_time = 0;

    private EventIsEndedEnum statusEnum = EventIsEndedEnum.NO;


    public EventDto(@NotNull Timestamp startDate, @NotBlank String sport, @NotBlank String country,
                    @NotBlank String league, @NotBlank String teamA,
                    @NotBlank String teamB, int teamA_pts, int teamB_pts, int live_duration_time) {
        this.startDate = startDate;
        this.sport = sport;
        this.country = country;
        this.league = league;
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamA_pts = teamA_pts;
        this.teamB_pts = teamB_pts;
        this.live_duration_time = live_duration_time;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLive_duration_time() {
        return live_duration_time;
    }

    public void setLive_duration_time(int live_duration_time) {
        this.live_duration_time = live_duration_time;
    }

    public void setStartDate(Timestamp startDate) {
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

    public EventIsEndedEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(EventIsEndedEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "startDate=" + startDate +
                ", sport='" + sport + '\'' +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", dataType=" + dataType +
                ", teamA_pts=" + teamA_pts +
                ", teamB_pts=" + teamB_pts +
                ", live_duration_time=" + live_duration_time +
                ", statusEnum=" + statusEnum +
                '}';
    }
}
