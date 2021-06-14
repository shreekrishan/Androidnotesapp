package common.survin_it.notesapp.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.InsertBlockActivity;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.home.BottomSheetDialog;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross;
    TextView tv_admincenter, tv_calendars, tv_account, tv_notification, tv_profile, tv_feedback, tv_invitefriends;
    FloatingActionButton btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
        tv_admincenter = findViewById(R.id.tv_admincenter);
        tv_calendars = findViewById(R.id.tv_calendars);
        tv_account = findViewById(R.id.tv_account);
        tv_notification = findViewById(R.id.tv_notification);
        tv_profile = findViewById(R.id.tv_profile);
        tv_feedback = findViewById(R.id.tv_feedback);
        tv_invitefriends = findViewById(R.id.tv_invitefriends);
        btn_menu = findViewById(R.id.btn_menu);


        iv_cross.setOnClickListener(this);
        tv_admincenter.setOnClickListener(this);
        tv_calendars.setOnClickListener(this);
        tv_account.setOnClickListener(this);
        tv_notification.setOnClickListener(this);
        tv_profile.setOnClickListener(this);
        tv_feedback.setOnClickListener(this);
        tv_invitefriends.setOnClickListener(this);
        btn_menu.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(SettingsActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_calendars:
                Intent inten = new Intent(SettingsActivity.this, Settings_CalendarActivity.class);
                startActivity(inten);
                break;
            case R.id.tv_admincenter:
                Intent inen = new Intent(SettingsActivity.this, Settings_AdmincenterActivity.class);
                startActivity(inen);
                break;
//            case R.id.btn_menu:
//                BottomSheetDialog bottomSheet = new BottomSheetDialog();
//                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
//                break;
            case R.id.tv_account:
                Intent in = new Intent(SettingsActivity.this, Settings_AccountActivity.class);
                startActivity(in);
                break;
            case R.id.tv_notification:
                Intent n = new Intent(SettingsActivity.this, Settings_NotificationActivity.class);
                startActivity(n);
                break;
            case R.id.tv_profile:
                Intent n1 = new Intent(SettingsActivity.this, Settings_ProfileActivity.class);
                startActivity(n1);
                break;

            case R.id.tv_feedback:
                Intent feed = new Intent(SettingsActivity.this, Settings_FeedbackActivity.class);
                startActivity(feed);
                break;
            case R.id.tv_invitefriends:
                Intent fed = new Intent(SettingsActivity.this, Settings_InviteFriendsActivity.class);
                startActivity(fed);
                break;
            case R.id.btn_menu:
                Intent insert = new Intent(SettingsActivity.this, InsertBlockActivity.class);
                startActivity(insert);
                break;
        }
    }

}