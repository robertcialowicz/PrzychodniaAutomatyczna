package com.iet.przychodnia.BookVisitSystem.service;

import com.iet.przychodnia.BookVisitSystem.model.Visit;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FindFreeVisits {

    public static List<Visit> findFreeVisitsBetweenDatesWhenReservedAreGiven(String fromDate, String toDate, List<Visit> reservedVisits){
        LocalDateTime from = LocalDateTime.parse(fromDate);
        LocalDateTime upto = LocalDateTime.parse(toDate);

        //take all datetimes from the List<Visit>
        List<LocalDateTime> listOfReserved = new ArrayList<>();
        for (Visit visit : reservedVisits){
            listOfReserved.add(LocalDateTime.parse(visit.getDatetime()));
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
            result.add(new Visit(null, i.toString(), null, null, null,null, null));
        }

        return result;
    };




}
