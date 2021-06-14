package common.survin_it.notesapp.model;

public class NewUser {

    private String number;
    private String name;
    private String district;
    private String town;
    private String status;

    public NewUser() {
    }

    public NewUser(String number, String name, String district, String town, String status) {
        this.number = number;
        this.name = name;
        this.district = district;
        this.town = town;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
