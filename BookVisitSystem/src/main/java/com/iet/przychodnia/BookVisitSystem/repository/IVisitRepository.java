package com.iet.przychodnia.BookVisitSystem.repository;

import com.iet.przychodnia.BookVisitSystem.model.Visit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVisitRepository {
    int insertVisit(UUID id, Visit visit);

    default int insertVisit(Visit visit){
        UUID id = UUID.randomUUID();
        return insertVisit(id, visit);
    }

    List<Visit> selectAllVisits();

    Optional<Visit> selectVisitById(UUID id);

    int deleteVisitById(UUID id);

    int updateVisitById(UUID id, Visit visit);


}
