package ru.maksmerfy.testappforntiteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import ru.maksmerfy.testappforntiteam.dto.YoungOverlord;
import ru.maksmerfy.testappforntiteam.models.Overlord;

import java.util.List;

public interface OverlordRepository extends JpaRepository<Overlord, String> {
    Overlord findOverlordsByName(String name);
    Overlord findOverlordsById(String id);

    @Query(value = "SELECT overlords.name from overlords left join planets on overlords.id = planets.overlord_id where planets.id is null order by overlords.name", nativeQuery = true)
    List<String> findAllLoafers();

    @Nullable
    @Query(value = "SELECT overlords.name, overlords.age from overlords order by overlords.age limit 10", nativeQuery = true)
    List<YoungOverlord> findYoungOverlord();
}
