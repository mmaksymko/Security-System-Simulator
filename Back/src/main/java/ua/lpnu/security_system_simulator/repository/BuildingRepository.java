package ua.lpnu.security_system_simulator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

@Repository
public interface BuildingRepository extends MongoRepository<BuildingLevel, String> {
}
