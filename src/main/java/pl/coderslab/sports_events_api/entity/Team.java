package pl.coderslab.sports_events_api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @ManyToOne
    private Sport sport;

    @ManyToMany
    List<League> leagues = new ArrayList<>();

    public Team() {
    }

    public Team(@NotBlank String name, Sport sport) {
        this.name = name;
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(sport, team.sport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, sport);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport=" + sport +
                '}';
    }
}
