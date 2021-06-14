package common.survin_it.notesapp.model;

import java.util.List;

public class NewMeeting {
    private String title,start_date,end_date,start_time,end_time,repeat,details;
    private Boolean all_day;
    private List<AlertModel> alerts;
    private List<AlertModel> tags;

    public NewMeeting() {
    }

    public NewMeeting(String title, String start_date, String end_date, String start_time, String end_time, String repeat, String details, Boolean all_day, List<AlertModel> alerts, List<AlertModel> tags) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.repeat = repeat;
        this.details = details;
        this.all_day = all_day;
        this.alerts = alerts;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getAll_day() {
        return all_day;
    }

    public void setAll_day(Boolean all_day) {
        this.all_day = all_day;
    }

    public List<AlertModel> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertModel> alerts) {
        this.alerts = alerts;
    }

    public List<AlertModel> getTags() {
        return tags;
    }

    public void setTags(List<AlertModel> tags) {
        this.tags = tags;
    }
}
