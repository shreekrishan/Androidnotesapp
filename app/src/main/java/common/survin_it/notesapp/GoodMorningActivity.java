package common.survin_it.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.survin_it.notesapp.common.Common;

public class GoodMorningActivity extends AppCompatActivity {

    TextView userName, currentDate,tvWeek;
    SharedPreferences sh;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_morning);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        userName = findViewById(R.id.tv_name);
        currentDate = findViewById(R.id.tv_date);
        tvWeek = findViewById(R.id.tv_week);

        sh = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        user_name = sh.getString("user_name", "");
        userName.setText(user_name+" !");

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        tvWeek.setText("It's "+dayOfTheWeek);

        Calendar cal= Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        SimpleDateFormat month_day = new SimpleDateFormat("dd");
        String month_name = month_date.format(cal.getTime());
        String monthDay = month_day.format(cal.getTime());
        currentDate.setText(monthDay+" , "+month_name);

        //SharedPreferences sh = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //String userID = sh.getString("userUUID", "");

//        Common.db.collection("new_users").document(userID)
//                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if(value.exists()){
//                            String status = value.getString("status");
//                            if (status.equals("0")){
//                                Intent intent = new Intent(GoodMorningActivity.this, GoodMorningActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            }
//                            else {
//                                Intent intent = new Intent(GoodMorningActivity.this, GoodMorning2Activity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            }
//                        }
//
//                    }
//                });
    }
}