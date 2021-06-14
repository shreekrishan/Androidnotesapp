package common.survin_it.notesapp.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import common.survin_it.notesapp.CalendarActivity;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.BottomSheet_date_event_calendar;
import common.survin_it.notesapp.home.EventDetailsActivity;

public class Settings_ChangeNumberActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton btn_menu;

    EditText etOldMobile,etNewMobile;
    TextView btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_change_number);
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
        etNewMobile = findViewById(R.id.et_new_mobile);
        etOldMobile = findViewById(R.id.et_old_mobile);
        btnSubmit = findViewById(R.id.tv_submit);
        btnSubmit.setOnClickListener(this);
        iv_cross.setOnClickListener(this);
        btn_menu.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_ChangeNumberActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_menu:
                BottomSheetDialogCalendars bottomSheet = new BottomSheetDialogCalendars();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
                break;

            case R.id.tv_submit:
                if (etOldMobile.getText().toString().equals("")){
                    Toast.makeText(Settings_ChangeNumberActivity.this,"enter old number",Toast.LENGTH_SHORT).show();
                }
                else if(etNewMobile.getText().toString().equals("")){
                    Toast.makeText(Settings_ChangeNumberActivity.this,"enter new number",Toast.LENGTH_SHORT).show();
                }
                else {
                    // check in firebase whether event exist or not on selected date
                    Common.db.collection("users").whereEqualTo("mobile",etOldMobile.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    boolean documentExists;

                                    if (task.isSuccessful()) {
                                        documentExists = !task.getResult().isEmpty();
                                        for (QueryDocumentSnapshot document : task.getResult()) {

                                        }

                                    }
                                    else {
                                        documentExists = false;
                                    }
                                    if(documentExists) {
                                        Intent intent = new Intent(Settings_ChangeNumberActivity.this, ReOTPActivity.class);
                                        intent.putExtra("mobile", etNewMobile.getText().toString());
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(Settings_ChangeNumberActivity.this,"enter correct number.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                break;
        }
    }

}