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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.Feedback;

public class Settings_FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross, iv_icon;
    LinearLayout ll_localevents, ll_localeventsdetail;
    EditText etDescription;
    TextView tvSubmit;
    FloatingActionButton btn_menu, add_button, btn_search;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_feedback);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        iv_cross = findViewById(R.id.iv_cross);
        btn_menu = findViewById(R.id.btn_menu);
        etDescription = findViewById(R.id.et_description);
        tvSubmit = findViewById(R.id.tv_submit);
        add_button = findViewById(R.id.add_button);
        add_button.setVisibility(View.GONE);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setVisibility(View.GONE);
        iv_icon = findViewById(R.id.iv_icon);
        iv_icon.setVisibility(View.GONE);
        iv_cross.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_FeedbackActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_submit:
                Feedback feedback = new Feedback();
                feedback.setDescription(etDescription.getText().toString());
                feedback.setId(firebaseAuth.getUid());
                feedback.setDeleted(false);
                feedback.setStatus(true);
                feedback.setName("");
                Common.db.collection("feedback_options").document()
                        .set(feedback)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                etDescription.setText("");
                                Toast.makeText(Settings_FeedbackActivity.this,"success",Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

}