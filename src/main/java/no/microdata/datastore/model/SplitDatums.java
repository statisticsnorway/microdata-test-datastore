package no.microdata.datastore.model;

import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a set of FDBDataClient splitted into separated lists for each property. A single datum can be recreated by
 * collecting values in each list on one given index.
 */
public class SplitDatums {

    List<Long> ids;
    List<String> values;
    List<LocalDate> startDates = new ArrayList<>();
    List<LocalDate> stopDates = new ArrayList<>();

    public SplitDatums(Map args) {
        ids = (List<Long>) args.get("ids");
        values = (List<String>) args.get("values");

        List<LocalDate> tmpStartDates = (List<LocalDate>)args.get("startDates");
        if ( ! CollectionUtils.isEmpty(tmpStartDates) ){
            startDates = tmpStartDates;
        }

        List<LocalDate> tmpStopDates = (List<LocalDate>)args.get("stopDates");
        if ( ! CollectionUtils.isEmpty(tmpStopDates) ){
            stopDates = tmpStopDates;
        }
    }

    public List<Long> getIds() {
        return ids;
    }

    public List<String> getValues() {
        return values;
    }

    public List<LocalDate> getStartDates() {
        return startDates;
    }

    public List<LocalDate> getStopDates() {
        return stopDates;
    }

    @Override
    public String toString(){
        return "Datum size = " + ids.size();
    }

    static List<Long> datesAsDays(List<LocalDate> dates) {
        return dates.stream().map(LocalDate::toEpochDay).collect(Collectors.toList());
    }

    public List<Long> stopDatesAsDays() {
        return datesAsDays(stopDates);
    }

    public List<Long> startDatesAsDays() {
        return datesAsDays(startDates);
    }
}