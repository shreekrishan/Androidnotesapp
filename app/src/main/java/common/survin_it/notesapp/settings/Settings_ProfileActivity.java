package common.survin_it.notesapp.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;

public class Settings_ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross, iv_icon;
    LinearLayout ll_mode, ll_fontsize, ll_language;
    FloatingActionButton btn_menu, btn_search, add_button;
    TextView tv_fontsize, tv_language, tv_mode;
    NumberPicker np_langauge, np_fontsize, np_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);
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
        ll_language = findViewById(R.id.ll_language);
        ll_fontsize = findViewById(R.id.ll_fontsize);
        ll_mode = findViewById(R.id.ll_mode);
        np_langauge = findViewById(R.id.np_langauge);
        np_fontsize = findViewById(R.id.np_fontsize);
        np_mode = findViewById(R.id.np_mode);
        tv_language = findViewById(R.id.tv_language);
        tv_fontsize = findViewById(R.id.tv_fontsize);
        tv_mode = findViewById(R.id.tv_mode);
        btn_search = findViewById(R.id.btn_search);
        add_button = findViewById(R.id.add_button);
        iv_icon = findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        tv_language.setOnClickListener(this);
        tv_fontsize.setOnClickListener(this);
        tv_mode.setOnClickListener(this);
        btn_search.setVisibility(View.GONE);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);

        loadLocal();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_ProfileActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_language:
                updatelabellang();
                if (ll_language.getVisibility() == View.VISIBLE) {
                    ll_language.setVisibility(View.GONE);
                    ll_fontsize.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.GONE);
                } else {
                    ll_language.setVisibility(View.VISIBLE);
                    ll_fontsize.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.GONE);
                    updatelabellang();

                }
                break;
            case R.id.tv_fontsize:
                updatelabelfont();
                if (ll_fontsize.getVisibility() == View.VISIBLE) {
                    ll_language.setVisibility(View.GONE);
                    ll_fontsize.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.GONE);
                } else {
                    ll_language.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.GONE);
                    ll_fontsize.setVisibility(View.VISIBLE);
                    updatelabelfont();

                }
                break;
            case R.id.tv_mode:
                updatelabelmode();
                if (ll_mode.getVisibility() == View.VISIBLE) {
                    ll_language.setVisibility(View.GONE);
                    ll_fontsize.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.GONE);
                } else {
                    ll_language.setVisibility(View.GONE);
                    ll_fontsize.setVisibility(View.GONE);
                    ll_mode.setVisibility(View.VISIBLE);
                    updatelabelmode();

                }
                break;
        }
    }

    private void updatelabelmode() {
        String[] pickerValmode = new String[]{"Auto", "Dark"};
        int len = pickerValmode.length;
        np_mode.setMaxValue(len - 1);
        np_mode.setMinValue(0);
        np_mode.setDisplayedValues(pickerValmode);
        np_mode.setWrapSelectorWheel(false);
        np_mode.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_mode.setText(pickerValmode[newVal]);
            }
        });
    }

    private void updatelabelfont() {
        String[] pickerValfont = new String[]{"Medium", "High"};
        int len = pickerValfont.length;
        np_fontsize.setMaxValue(len - 1);
        np_fontsize.setMinValue(0);
        np_fontsize.setDisplayedValues(pickerValfont);
        np_fontsize.setWrapSelectorWheel(false);
        np_fontsize.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_fontsize.setText(pickerValfont[newVal]);
            }
        });
    }

    private void updatelabellang() {
        String[] pickerVallan = new String[]{"English", "Kannad"};
        int len = pickerVallan.length;
        np_langauge.setMaxValue(len - 1);
        np_langauge.setMinValue(0);
        np_langauge.setDisplayedValues(pickerVallan);
        np_langauge.setWrapSelectorWheel(false);
        np_langauge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_language.setText(pickerVallan[newVal]);
                if(pickerVallan[newVal].equals("English")){
                    setLocale("en");
                }
                else {
                    setLocale("kn");
                }
            }
        });
    }
    public void loadLocal(){
        SharedPreferences preferences = getSharedPreferences("MySharedPref", Activity.MODE_PRIVATE);
        String lang = preferences.getString("language","");
        setLocale(lang);
        if (lang.equals("en")){
            tv_language.setText("English");
        }
        else {
            tv_language.setText("Kannad");
        }
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

       // recreate();
    }



}