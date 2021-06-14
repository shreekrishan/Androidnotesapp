package common.survin_it.notesapp.model;

public class Feedback {
    private Boolean deleted,status;
    private String description,name,id;

    public Feedback() {
    }

    public Feedback(Boolean deleted, Boolean status, String description, String name, String id) {
        this.deleted = deleted;
        this.status = status;
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
