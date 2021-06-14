package common.survin_it.notesapp.all_spaces;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chivorn.datetimeoptionspicker.DateTimePickerView;
import com.chivorn.datetimeoptionspicker.OptionsPickerView;
import com.chivorn.datetimeoptionspicker.listener.CustomListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Alert_adapter;
import common.survin_it.notesapp.adapter.Tags_adapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.MySpace;
import common.survin_it.notesapp.model.Repeat;

public class CustomActivity extends AppCompatActivity {

    TextView tv_weeks, tv_weekon, tv_never;
    LinearLayout linearTwo;

    DatePicker datePicker, datePicker1;
    NumberPicker np_weeks, np_week,np_never;
    FloatingActionButton btnAdd, btnMenu, btnSearch;
    ImageView ivPosition;

    LinearLayout frame_cal,frame_cal3,frame_cal1,frame_never;

    ImageView iv_add_detail;
    String[] pickerVals = new String[] {"Daily", "Weekly", "Monthly", "Yearly"};
    String[] pickerValsweek = new String[] {"Sunday", "Monday", "Tuesday", "Wedbesday","Thursday","Friday","Saturday"};
    String[] pickerValnever = new String[] {"Never", "Weekly"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        tv_weeks = findViewById(R.id.tv_weeks);
        tv_weekon = findViewById(R.id.tv_weekon);
        frame_cal = findViewById(R.id.frame_cal);
        np_week = findViewById(R.id.np_week);
        np_never = findViewById(R.id.np_never);
        datePicker = findViewById(R.id.datePicker);
        np_weeks = findViewById(R.id.np_weeks);
        btnAdd = findViewById(R.id.add_button);
        btnMenu = findViewById(R.id.btn_menu);
        frame_cal3 = findViewById(R.id.frame_cal3);
        frame_cal1 = findViewById(R.id.frame_cal1);
        btnSearch = findViewById(R.id.btn_search);
        tv_never = findViewById(R.id.tv_never);
        frame_never = findViewById(R.id.frame_never);
        ivPosition = findViewById(R.id.iv_icon);
        btnAdd.setVisibility(View.GONE);
        ivPosition.setVisibility(View.GONE);

        tv_weeks.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                updatelabel();
                if (frame_cal.getVisibility() == View.VISIBLE) {
                    frame_cal.setVisibility(View.GONE);
                    frame_cal1.setVisibility(View.GONE);
                    frame_never.setVisibility(View.GONE);
                    //updatelabel();
                } else {
                    frame_cal.setVisibility(View.VISIBLE);
                    frame_cal1.setVisibility(View.GONE);
                    frame_never.setVisibility(View.GONE);
                    updatelabel();


                }
            }
        });
        tv_weekon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       updatelabel2();
        if (frame_cal1.getVisibility() == View.VISIBLE) {
            frame_cal1.setVisibility(View.GONE);
            frame_cal.setVisibility(View.GONE);
            frame_never.setVisibility(View.GONE);
            //updatelabel();
        } else {
            frame_cal1.setVisibility(View.VISIBLE);
            frame_cal.setVisibility(View.GONE);
            frame_never.setVisibility(View.GONE);
            updatelabel2();


        }
    }
});
        tv_never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatelabel3();
                if (frame_never.getVisibility() == View.VISIBLE) {
                    frame_cal1.setVisibility(View.GONE);
                    frame_cal.setVisibility(View.GONE);
                    frame_never.setVisibility(View.GONE);
                    //updatelabel();
                } else {
                    frame_cal1.setVisibility(View.GONE);
                    frame_cal.setVisibility(View.GONE);
                    frame_never.setVisibility(View.VISIBLE);
                    updatelabel3();


                }
            }
        });
    }

    private void updatelabel3() {
        int len1=pickerValnever.length;
        np_never.setMaxValue(len1-1);
        np_never.setMinValue(0);
        np_never.setDisplayedValues(pickerValnever);
        np_never.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_never.setText(pickerValnever[newVal]);
            }
        });
    }

    private void updatelabel2() {
        int len1=pickerValsweek.length;
        np_week.setMaxValue(len1-1);
        np_week.setMinValue(0);
        np_week.setDisplayedValues(pickerValsweek);
        np_week.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_weekon.setText(pickerValsweek[newVal]);
            }
        });

    }

    private void updatelabel() {
        int len=pickerVals.length;
        np_weeks.setMaxValue(len-1);
        np_weeks.setMinValue(0);
        np_weeks.setDisplayedValues(pickerVals);
        np_weeks.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv_weeks.setText(pickerVals[newVal]);
            }
        });

    }
}
