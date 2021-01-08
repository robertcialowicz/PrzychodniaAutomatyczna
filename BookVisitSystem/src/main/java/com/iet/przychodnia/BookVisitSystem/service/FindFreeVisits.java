package com.iet.przychodnia.BookVisitSystem.service;

import com.iet.przychodnia.BookVisitSystem.model.Visit;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FindFreeVisits {

    public static List<Visit> findFreeVisitsBetweenDatesWhenReservedAreGiven(String fromDate, String toDate, List<Visit> reservedVisits){

        LocalDateTime from = (fromDate.length()==10) ? LocalDateTime.parse(fromDate + "T00:00:00") : LocalDateTime.parse(fromDate.replace(' ','T'));
        LocalDateTime upto = (toDate.length()==10) ? LocalDateTime.parse(toDate + "T00:00:00") : LocalDateTime.parse(toDate.replace(' ', 'T'));

        //take all datetimes from the List<Visit>
        List<LocalDateTime> listOfReserved = new ArrayList<>();
        for (Visit visit : reservedVisits){
            listOfReserved.add(LocalDateTime.parse(visit.getDatetime().replace(' ', 'T')));
        }
        List<LocalDateTime> listOfAll = Stream.iterate(from, d-> d.plusHours(1))
                .takeWhile(d -> d.isBefore(upto))
                .filter(d -> d.getHour() > 7)
                .filter(d -> d.getHour() < 17)
                .filter(d -> d.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(d -> d.getDayOfWeek() != DayOfWeek.SUNDAY)
                .collect(Collectors.toList());

        listOfAll.removeAll(listOfReserved);

        List<Visit> result = new ArrayList<>();

        for(LocalDateTime i : listOfAll){
            result.add(new Visit(null, i.toString().replace('T', ' '), null, null, null,null, null));
        }

        return result;
    };




}
