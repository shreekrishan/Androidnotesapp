package common.survin_it.notesapp.model;

public class UserNotification {
    private String description,title;

    public UserNotification() {
    }

    public UserNotification(String description, String title) {
        this.description = description;
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
