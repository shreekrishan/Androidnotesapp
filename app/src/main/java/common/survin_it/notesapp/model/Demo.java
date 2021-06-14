package common.survin_it.notesapp.model;

public class Demo {
    private String name;
    private String number;

    public Demo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Demo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
