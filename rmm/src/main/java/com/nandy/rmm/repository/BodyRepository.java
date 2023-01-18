package com.nandy.rmm.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nandy.rmm.model.BodyInfo;

public interface BodyRepository extends MongoRepository<BodyInfo, String> {

    @Query("{thickness : ?0}")
    public List<BodyInfo> findBodiesWithThicknessEqualTo(String thicknessInches);
    
	@Query("{type : ?0}")
	public List<BodyInfo> findByType(String type);	

}
