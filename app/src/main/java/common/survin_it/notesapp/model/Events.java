package common.survin_it.notesapp.model;

public class Events {

    private String administrated_by,alert,all_Day_Event,end_date,end_time,location,repeat,start_date,start_time,subject_title,tags,calendarId;
    private DescriptionEvent descriptions;

    public Events() {
    }

    public Events(String administrated_by, String alert, String all_Day_Event, String end_date, String end_time, String location, String repeat, String start_date, String start_time, String subject_title, String tags, String calendarId, DescriptionEvent descriptions) {
        this.administrated_by = administrated_by;
        this.alert = alert;
        this.all_Day_Event = all_Day_Event;
        this.end_date = end_date;
        this.end_time = end_time;
        this.location = location;
        this.repeat = repeat;
        this.start_date = start_date;
        this.start_time = start_time;
        this.subject_title = subject_title;
        this.tags = tags;
        this.calendarId = calendarId;
        this.descriptions = descriptions;
    }

    public String getAdministrated_by() {
        return administrated_by;
    }

    public void setAdministrated_by(String administrated_by) {
        this.administrated_by = administrated_by;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getAll_Day_Event() {
        return all_Day_Event;
    }

    public void setAll_Day_Event(String all_Day_Event) {
        this.all_Day_Event = all_Day_Event;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public DescriptionEvent getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(DescriptionEvent descriptions) {
        this.descriptions = descriptions;
    }
}
