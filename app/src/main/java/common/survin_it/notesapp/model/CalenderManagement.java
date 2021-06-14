package common.survin_it.notesapp.model;

import java.util.Date;

public class CalenderManagement {

    private String admin,calendarName,categories,panchanga_data;
    private Boolean status;
    private Date created_at;

    public CalenderManagement() {
    }

    public CalenderManagement(String admin, String calendarName, String categories, String panchanga_data, Boolean status, Date created_at) {
        this.admin = admin;
        this.calendarName = calendarName;
        this.categories = categories;
        this.panchanga_data = panchanga_data;
        this.status = status;
        this.created_at = created_at;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPanchanga_data() {
        return panchanga_data;
    }

    public void setPanchanga_data(String panchanga_data) {
        this.panchanga_data = panchanga_data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
