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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import common.survin_it.notesapp.ChooseLanguageActivity;
import common.survin_it.notesapp.GoodMorning2Activity;
import common.survin_it.notesapp.GoodMorningActivity;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.Splash_Screen;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.register.SelectLocationActivity;

public class Settings_AccountActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross, iv_icon;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton btn_menu, add_button;
    TextView tv_changenumber, tv_location, tv_deleteaccount,tvValidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_account);
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
        btn_menu = findViewById(R.id.btn_menu);
        tvValidity = findViewById(R.id.tv_date);
        tv_changenumber = findViewById(R.id.tv_changenumber);
        tv_location = findViewById(R.id.tv_location);
        tv_deleteaccount = findViewById(R.id.tv_deleteaccount);
        add_button = findViewById(R.id.add_button);
        iv_icon = findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        tv_changenumber.setOnClickListener(this);
        tv_location.setOnClickListener(this);
        tv_deleteaccount.setOnClickListener(this);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);

        Common.db.collection("users").document(Common.firebaseAuth.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value.exists()){
                            String date = value.getString("valid_till");
                            tvValidity.setText(date);
                        }
                        else {
                            tvValidity.setText("01 January 2030");
                        }

                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_AccountActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_changenumber:
                Intent inent = new Intent(Settings_AccountActivity.this, Settings_ChangeNumberActivity.class);
                startActivity(inent);
                break;


            case R.id.tv_location:
                Intent innt = new Intent(Settings_AccountActivity.this, SelectLocationActivity.class);
                Common.isChangeLocation = "1";
                startActivity(innt);
                break;
            case R.id.tv_deleteaccount:
                Intent inn = new Intent(Settings_AccountActivity.this, Settings_Delete_AccountActivity.class);
                startActivity(inn);
                break;
        }
    }

}