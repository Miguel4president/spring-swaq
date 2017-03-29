package com.miguel.dataTransform;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Annotation only changes url path
//@RepositoryRestResource(collectionResourceRel = "gamerecordsPlace", path = "gamerecordsPlace")
public interface GameRecordRepo extends PagingAndSortingRepository<GameRecord, Long> {

  List<GameRecord> findByUsername(@Param("username") String username);
}
