package common.survin_it.notesapp.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.Dummy.Dummy;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.home.BottomSheetDialog;
import common.survin_it.notesapp.home.BottomSheetDialogShare;

public class Settings_CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross, iv_icon;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton btn_menu, add_button;
    TextView tv_sports, tv_all, tv_events, tv_music, tv_holiday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_calendars);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        iv_cross = findViewById(R.id.iv_cross);
        ll_localevents = findViewById(R.id.ll_localevents);
        ll_localeventsdetail = findViewById(R.id.ll_localeventsdetail);
        btn_menu = findViewById(R.id.btn_menu);
        add_button = findViewById(R.id.add_button);
        iv_icon = findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        ll_localevents.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        tv_sports = findViewById(R.id.tv_sports);
        tv_all = findViewById(R.id.tv_all);
        tv_events = findViewById(R.id.tv_events);
        tv_sports.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_events.setOnClickListener(this);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);
        tv_music = findViewById(R.id.tv_music);
        tv_holiday = findViewById(R.id.tv_holiday);
        tv_music.setOnClickListener(this);
        tv_holiday.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_CalendarActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_menu:
                BottomSheetDialogCalendars bottomSheet = new BottomSheetDialogCalendars();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
                break;
            case R.id.ll_localevents:
                if (ll_localeventsdetail.getVisibility() == View.VISIBLE) {
                    ll_localeventsdetail.setVisibility(View.GONE);
                } else {
                    ll_localeventsdetail.setVisibility(View.VISIBLE);

                }
                break;

            case R.id.tv_all:
                if (Dummy.btn_all == 1) {
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
                    Dummy.btn_music = 1;
                    tv_music.setBackgroundResource(R.drawable.button_selector);
                    tv_music.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_holiday = 1;
                    tv_holiday.setBackgroundResource(R.drawable.button_selector);
                    tv_holiday.setTextColor(getResources().getColor(R.color.white));
                    break;
                } else {
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
                    Dummy.btn_music = 0;
                    tv_music.setBackgroundResource(R.drawable.school_button_white);
                    tv_music.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_holiday = 0;
                    tv_holiday.setBackgroundResource(R.drawable.school_button_white);
                    tv_holiday.setTextColor(getResources().getColor(R.color.black));
                    break;
                }

            case R.id.tv_sports:
                if (Dummy.btn_sports == 1) {
                    Dummy.btn_sports = 0;
                    tv_sports.setBackgroundResource(R.drawable.school_button_white);
                    tv_sports.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));

                } else {
                    Dummy.btn_sports = 1;
                    tv_sports.setBackgroundResource(R.drawable.button_selector);
                    tv_sports.setTextColor(getResources().getColor(R.color.white));

                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            case R.id.tv_events:
                if (Dummy.btn_event == 1) {
                    Dummy.btn_event = 0;
                    tv_events.setBackgroundResource(R.drawable.school_button_white);
                    tv_events.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                } else {
                    Dummy.btn_event = 1;
                    tv_events.setBackgroundResource(R.drawable.button_selector);
                    tv_events.setTextColor(getResources().getColor(R.color.white));
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;

                }
            case R.id.tv_music:
                if (Dummy.btn_music == 1) {
                    Dummy.btn_music = 0;
                    tv_music.setBackgroundResource(R.drawable.school_button_white);
                    tv_music.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                } else {
                    Dummy.btn_music = 1;
                    tv_music.setBackgroundResource(R.drawable.button_selector);
                    tv_music.setTextColor(getResources().getColor(R.color.white));
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;

                }
            case R.id.tv_holiday:
                if (Dummy.btn_holiday == 1) {
                    Dummy.btn_holiday = 0;
                    tv_holiday.setBackgroundResource(R.drawable.school_button_white);
                    tv_holiday.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                } else {
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

