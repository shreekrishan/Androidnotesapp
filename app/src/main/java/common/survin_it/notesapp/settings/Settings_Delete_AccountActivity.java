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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.register.SelectLocationActivity;

public class Settings_Delete_AccountActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton btn_menu;
    TextView tv_changenumber, tv_location, tv_deleteaccount;
    TextView tvYes, tvNo, tvSubmit;
    // firebase integrate
    private FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_delete_account);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        iv_cross = findViewById(R.id.iv_cross);
        btn_menu = findViewById(R.id.btn_menu);
        tv_changenumber = findViewById(R.id.tv_changenumber);
        tv_location = findViewById(R.id.tv_location);
        tv_deleteaccount = findViewById(R.id.tv_deleteaccount);
        iv_cross.setOnClickListener(this);
        tvNo = findViewById(R.id.tv_no);
        tvYes = findViewById(R.id.tv_yes);
        tvSubmit = findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_Delete_AccountActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_submit:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                String currentDateTime = simpleDateFormat.format(new Date());
                db.collection("users").document(firebaseAuth.getUid())
                        .update("deleted_at",currentDateTime,
                                "deleted","1")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Settings_Delete_AccountActivity.this, "success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Settings_Delete_AccountActivity.this, Settings_AccountActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(Settings_Delete_AccountActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

}