package com.iet.przychodnia.BookVisitSystem.repository;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("list")
public class VisitRepositoryAccessAsList implements IVisitRepository {

    private static List<Visit> DB = new ArrayList<>();

    @Override
    public Visit insertVisit(UUID id, Visit visit) {
        //DB.add(new Visit(id, visit.getDatetime(), visit.getNotes()));
        return visit;
    }

    @Override
    public List<Visit> selectAllVisits() {
        return DB;
    }

    @Override
    public Optional<Visit> selectVisitById(UUID id) {
        return DB.stream()
                .filter(visit -> visit.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteVisitById(UUID id) {
        Optional<Visit> visitMaybe = selectVisitById(id);
        if (visitMaybe.isPresent()){
            DB.remove(visitMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public Visit updateVisitById(UUID id, Visit visitToUpdate) {
        return selectVisitById(id)
                .map(visit -> {
                    int indexOfVisitToUpdate = DB.indexOf(visit);
                    if (indexOfVisitToUpdate >= 0){
                        //DB.set(indexOfVisitToUpdate, new Visit(id, visitToUpdate.getDatetime(),visitToUpdate.getNotes()));
                        return DB.get(indexOfVisitToUpdate);
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public List<Visit> searchForVisitsInGivenPeriod(String fromDate, String toDate) {
        return null;
    }
}