package common.survin_it.notesapp.all_spaces;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.meeting.CreateMeetingActivity;
import common.survin_it.notesapp.home.testcalendar;
import common.survin_it.notesapp.settings.SettingsActivity;
import common.survin_it.notesapp.share.ShareActivity;
import common.survin_it.notesapp.spaces.SpacesActivity;

public class SelectSpaceActivity extends AppCompatActivity {

    LinearLayout linearSpace,linearMeeting,linearShare;

    FloatingActionButton add_button,menuButton;
    TextView tv_meetings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_space);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        linearSpace = findViewById(R.id.linear_1);
        linearMeeting = findViewById(R.id.linear_3);
        linearShare = findViewById(R.id.linear_2);

        add_button = findViewById(R.id.add_button);
        menuButton = findViewById(R.id.btn_menu);
        tv_meetings = findViewById(R.id.tv_meetings);

//        tv_meetings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent ient = new Intent(SelectSpaceActivity.this, InsertBlockActivity.class);
//                startActivity(ient);
//            }
//        });

        linearSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSpaceActivity.this, SpaceCreatedActivity.class);
                startActivity(intent);
            }
        });
        linearMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSpaceActivity.this, TodolistActivity.class);
              //  Intent intent = new Intent(SelectSpaceActivity.this, CreateMeetingActivity.class);
                startActivity(intent);
            }
        });
        linearShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSpaceActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(SelectSpaceActivity.this, SpacesActivity.class);
//                startActivity(intent1);
//            }
//        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SelectSpaceActivity.this, SettingsActivity.class);
                startActivity(intent1);
            }
        });

    }
}