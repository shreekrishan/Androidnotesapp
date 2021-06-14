package common.survin_it.notesapp.model;

import com.chivorn.datetimeoptionspicker.model.IPickerViewData;

public class Repeat implements IPickerViewData {
    private String name;

    public Repeat() {
    }

    public Repeat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
