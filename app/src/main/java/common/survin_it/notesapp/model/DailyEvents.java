package common.survin_it.notesapp.model;

public class DailyEvents {
    private String time,title;

    public DailyEvents() {
    }

    public DailyEvents(String time, String title) {
        this.time = time;
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
