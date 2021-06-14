package common.survin_it.notesapp.all_spaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Adapter_MySpace_events;
import common.survin_it.notesapp.model.My_space_events;
import common.survin_it.notesapp.settings.SettingsActivity;

public class Myspace_detailsActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linearSpace;
RecyclerView rv_eventdetails;
    FloatingActionButton add_button,menuButton;
Adapter_MySpace_events adapter;
    List<My_space_events> eventList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myspace_details);

        linearSpace = findViewById(R.id.linear_1);
        rv_eventdetails = findViewById(R.id.rv_eventdetails);

        add_button = findViewById(R.id.add_button);
        menuButton = findViewById(R.id.btn_menu);

        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        adapter = new Adapter_MySpace_events(this, eventList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_eventdetails.setLayoutManager(linearLayoutManager);
        rv_eventdetails.setAdapter(adapter);
        rv_eventdetails.setNestedScrollingEnabled(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_button:
                Intent intent1 = new Intent(Myspace_detailsActivity.this, SettingsActivity.class);
                startActivity(intent1);
                    break;

        }
    }
}