package common.survin_it.notesapp.model;

import java.util.List;

public class My_space_events {

    private String event_date;
    private List<EventsModel> eventdetails;


    public My_space_events(String event_date){
        this.event_date = event_date;
    }
    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public List<EventsModel> getEventdetails() {
        return eventdetails;
    }

    public void setEventdetails(List<EventsModel> eventdetails) {
        this.eventdetails = eventdetails;
    }

}
