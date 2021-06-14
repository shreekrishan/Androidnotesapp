package common.survin_it.notesapp.model;

public class AlertModel {
    private String alertTime;

    public AlertModel() {
    }

    public AlertModel(String alertTime) {
        this.alertTime = alertTime;
    }

    public String getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime;
    }
}
