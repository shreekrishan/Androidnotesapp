package common.survin_it.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.HomeActivity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intent = new Intent(Splash_Screen.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    SharedPreferences sh = Splash_Screen.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    String userID = sh.getString("userUUID", "");
                    if (userID.equals("")){
                        Intent intent = new Intent(Splash_Screen.this, ChooseLanguageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Common.db.collection("new_users").document(userID)
                                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                        if(value.exists()){
                                            String status = value.getString("status");
                                            if (status.equals("0")){
                                                Intent intent = new Intent(Splash_Screen.this, GoodMorningActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                            else if(status.equals("1")) {
                                                Intent intent = new Intent(Splash_Screen.this, GoodMorning2Activity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                        }
                                        else {
                                            Intent intent = new Intent(Splash_Screen.this, ChooseLanguageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                });
                    }
                    //Intent intent = new Intent(Splash_Screen.this, ChooseLanguageActivity.class);
                    //startActivity(intent);
                }

            }
        },4000);
    }
}