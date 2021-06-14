package common.survin_it.notesapp.spaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.settings.Settings_CalendarActivity;

public class CreateNotesActivity extends AppCompatActivity {

    FloatingActionButton btn_menu, add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNotesActivity.this, Settings_CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}