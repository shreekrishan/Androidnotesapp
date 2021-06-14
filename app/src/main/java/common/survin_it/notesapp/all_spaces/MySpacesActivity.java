package common.survin_it.notesapp.all_spaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.Dummy.Dummy;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.EventDetailsActivity;
import common.survin_it.notesapp.share.ShareActivity;

public class MySpacesActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton search_button,menu_button,add_button;
    ImageView iv_btn;
    TextView tv_sports, tv_all,tv_events,tv_music,tv_holiday;
    TextView tv_panchanga,tv_local_events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spaces);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        search_button = findViewById(R.id.btn_search);
        menu_button = findViewById(R.id.btn_menu);
        add_button = findViewById(R.id.add_button);
        add_button.setVisibility(View.GONE);
        iv_btn = findViewById(R.id.iv_icon);
        iv_btn.setVisibility(View.GONE);

        tv_panchanga = findViewById(R.id.tv_panchanga);
        tv_local_events = findViewById(R.id.tv_local_events);

        tv_sports=findViewById(R.id.tv_sports);
        tv_all=findViewById(R.id.tv_all);
        tv_events=findViewById(R.id.tv_events);
        tv_music=findViewById(R.id.tv_music);
        tv_holiday=findViewById(R.id.tv_holiday);
        tv_sports.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_events.setOnClickListener(this);
        tv_holiday.setOnClickListener(this);
        tv_music.setOnClickListener(this);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySpacesActivity.this, AllEventsListActivity.class);
                Common.spaceType = "3";
                startActivity(intent);
            }
        });

        tv_panchanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MySpacesActivity.this, EventDetailsActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(MySpacesActivity.this, AllEventsListActivity.class);
                Common.spaceType = "1";
                startActivity(intent);
            }
        });

        tv_local_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySpacesActivity.this, AllEventsListActivity.class);
                Common.spaceType = "2";
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_all:
                if(Dummy.btn_all==1){
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_sports = 1;
                    tv_sports.setBackgroundResource(R.drawable.button_selector);
                    tv_sports.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_event = 1;
                    tv_events.setBackgroundResource(R.drawable.button_selector);
                    tv_events.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_holiday = 1;
                    tv_holiday.setBackgroundResource(R.drawable.button_selector);
                    tv_holiday.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_music = 1;
                    tv_music.setBackgroundResource(R.drawable.button_selector);
                    tv_music.setTextColor(getResources().getColor(R.color.white));
                    break;
                }
                else {
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_sports = 0;
                    tv_sports.setBackgroundResource(R.drawable.school_button_white);
                    tv_sports.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_event = 0;
                    tv_events.setBackgroundResource(R.drawable.school_button_white);
                    tv_events.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_holiday = 0;
                    tv_holiday.setBackgroundResource(R.drawable.school_button_white);
                    tv_holiday.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_music = 0;
                    tv_music.setBackgroundResource(R.drawable.school_button_white);
                    tv_music .setTextColor(getResources().getColor(R.color.black));
                    break;
                }

            case R.id.tv_sports:
                if(Dummy.btn_sports==1){
                    Dummy.btn_sports = 0;
                    tv_sports.setBackgroundResource(R.drawable.school_button_white);
                    tv_sports.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));

                }
                else {
                    Dummy.btn_sports = 1;
                    tv_sports.setBackgroundResource(R.drawable.button_selector);
                    tv_sports.setTextColor(getResources().getColor(R.color.white));

                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            case R.id.tv_events:
                if(Dummy.btn_event==1){
                    Dummy.btn_event = 0;
                    tv_events.setBackgroundResource(R.drawable.school_button_white);
                    tv_events.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    Dummy.btn_event = 1;
                    tv_events.setBackgroundResource(R.drawable.button_selector);
                    tv_events.setTextColor(getResources().getColor(R.color.white));
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            case R.id.tv_music:
                if(Dummy.btn_music==1){
                    Dummy.btn_music = 0;
                    tv_music.setBackgroundResource(R.drawable.school_button_white);
                    tv_music.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));

                }
                else {
                    Dummy.btn_music = 1;
                    tv_music.setBackgroundResource(R.drawable.button_selector);
                    tv_music.setTextColor(getResources().getColor(R.color.white));

                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            case R.id.tv_holiday:
                if(Dummy.btn_holiday==1){
                    Dummy.btn_holiday = 0;
                    tv_holiday.setBackgroundResource(R.drawable.school_button_white);
                    tv_holiday.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    Dummy.btn_holiday = 1;
                    tv_holiday.setBackgroundResource(R.drawable.button_selector);
                    tv_holiday.setTextColor(getResources().getColor(R.color.white));
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
        }

    }
}