package pl.coderslab.sports_events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_events_api.entity.Country;

public interface CountryRepository extends JpaRepository <Country, Integer> {
}
