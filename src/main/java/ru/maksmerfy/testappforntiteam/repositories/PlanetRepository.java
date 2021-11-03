package ru.maksmerfy.testappforntiteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.maksmerfy.testappforntiteam.models.Overlord;
import ru.maksmerfy.testappforntiteam.models.Planet;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, String> {
    Planet findPlanetByName(String name);
    @Query(value = "SELECT name from planets where overlord_id = :overlordid order by name", nativeQuery = true)
    List<String> findPlanetsNameByOverlord(@Param("overlordid") String overlordid);
}
