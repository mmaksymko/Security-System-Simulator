package ua.lpnu.security_system_simulator.repository;

import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

import java.util.List;

@Repository
public interface BuildingRepository extends MongoRepository<BuildingLevel, String> {
    @Query(value = "{}", fields = "{ '_id' : 1, 'name' : 1 }" )
    List<BuildingLevel> idAndName();
}
