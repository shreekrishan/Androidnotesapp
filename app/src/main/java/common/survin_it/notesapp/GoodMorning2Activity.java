package common.survin_it.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.register.OtpActivity;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class GoodMorning2Activity extends AppCompatActivity {

    Button btnValidate;

    TextView userName, currentDate,tvWeek;

    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_morning2);

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

        SharedPreferences sh = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);
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


        btnValidate = findViewById(R.id.btn_validate);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (Common.userUniqueID.equals("0")){
                    SharedPreferences sh = GoodMorning2Activity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    String userID = sh.getString("userUUID", "");
                    Common.db.collection("new_users").document(userID)
                            .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                    //String status = value.getString("status");
                                    String mobile = value.getString("number");

                                    if(mobile.isEmpty() || mobile.length() < 10){
//                                    tv_number.setError("Enter a valid mobile");
//                                    tv_number.requestFocus();
                                        Toast.makeText(GoodMorning2Activity.this,"No right data found",Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    Intent intent = new Intent(GoodMorning2Activity.this, OtpActivity.class);
                                    intent.putExtra("mobile", mobile);
                                    startActivity(intent);
                                }
                            });

           //     }
//                else {
////                    SharedPreferences sh = GoodMorning2Activity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
////                    String userID = sh.getString("userUUID", "");
//                    Common.db.collection("users").document(Common.userUniqueID)
//                            .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                                @Override
//                                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                                    //String status = value.getString("status");
//                                    String mobile = value.getString("mobile");
//
//                                    if(mobile.isEmpty() || mobile.length() < 10){
////                                    tv_number.setError("Enter a valid mobile");
////                                    tv_number.requestFocus();
//                                        Toast.makeText(GoodMorning2Activity.this,"No right data found",Toast.LENGTH_SHORT).show();
//                                        return;
//                                    }
//
//                                    Intent intent = new Intent(GoodMorning2Activity.this, OtpActivity.class);
//                                    intent.putExtra("mobile", mobile);
//                                    startActivity(intent);
//                                }
//                            });
//
//                }

            }
        });
    }
}