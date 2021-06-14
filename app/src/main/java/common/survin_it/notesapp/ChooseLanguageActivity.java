package common.survin_it.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
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

import com.google.firebase.auth.FirebaseAuth;

import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.HomeActivity;
import common.survin_it.notesapp.register.RegistrationActivity_1;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Locale;

public class ChooseLanguageActivity extends AppCompatActivity {

    TextView tvKannad,tvEnglish;
    View viewKannad,viewEnglish;
    Button btnNext;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_choose_language);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tvKannad = findViewById(R.id.tv_kannad);
        viewKannad = findViewById(R.id.view_one);
        tvEnglish = findViewById(R.id.tv_english);
        viewEnglish = findViewById(R.id.view_two);
        btnNext = findViewById(R.id.btn_next);

        tvKannad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user choice
                Common.selectedLanguage = "0";

                tvKannad.setTypeface(tvKannad.getTypeface(), Typeface.BOLD);
                viewKannad.setVisibility(View.VISIBLE);
                tvEnglish.setTypeface(tvEnglish.getTypeface(), Typeface.NORMAL);
                viewEnglish.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store user choice
                Common.selectedLanguage = "1";

                tvEnglish.setTypeface(tvEnglish.getTypeface(), Typeface.BOLD);
                viewEnglish.setVisibility(View.VISIBLE);
                tvKannad.setTypeface(tvKannad.getTypeface(), Typeface.NORMAL);
                viewKannad.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Common.selectedLanguage.equals("0")){
                    setLocale("kn");
                   // recreate();

                    Intent intent = new Intent(ChooseLanguageActivity.this, RegistrationActivity_1.class);
                    startActivity(intent);
                }
                else {
                    setLocale("en");
                   // recreate();

                    Intent intent = new Intent(ChooseLanguageActivity.this, RegistrationActivity_1.class);
                    startActivity(intent);
                }
//
//                SharedPreferences sh = ChooseLanguageActivity.this.getSharedPreferences("MyShared", MODE_PRIVATE);
//                String lng = sh.getString("language", "");
//                Toast.makeText(ChooseLanguageActivity.this,lng,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("language", lang);
        myEdit.commit();
    }

    public void loadLocal(){
        SharedPreferences preferences = getSharedPreferences("MySharedPref", Activity.MODE_PRIVATE);
        String lang = preferences.getString("language","");
        setLocale(lang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }



//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//
//        }

    }
}