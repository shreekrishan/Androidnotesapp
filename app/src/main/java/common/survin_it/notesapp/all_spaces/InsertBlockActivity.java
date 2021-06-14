package common.survin_it.notesapp.all_spaces;

import android.content.Intent;
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
import common.survin_it.notesapp.home.BottomSheetDialogShare;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.MySpace;
import common.survin_it.notesapp.model.Repeat;

public class InsertBlockActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView startSpaceDate, startTime,endSpaceDate,endTime,tvRepeat;
    private OptionsPickerView dtpvOptions,dtpvNoLinkOptions;
    private FrameLayout mFrameLayout;
    LinearLayout linearTwo,linearThree;



    FloatingActionButton add_button,btnMenu,btnSearch;
    ImageView ivPosition;

    //details
    LinearLayout linearFour,linearDetail;
    LinearLayout frameEdit;
    LinearLayout frame_cal,frameRepeat,frame_time, frame_cal1,frame_time1;
    EditText etDetail;
    ImageView iv_add_detail;
    TextView tvDetail;

    //image save
    ImageView ivSaveButton;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertblock);


        add_button=findViewById(R.id.add_button);
        btnMenu=findViewById(R.id.btn_menu);
        add_button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_button:
                Intent intent = new Intent(InsertBlockActivity.this, TextColorActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu:
                BottomSheetDialogShare bottomSheet = new BottomSheetDialogShare();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
                break;
            case R.id.iv_cross1:
                Intent i=new Intent(this,SelectSpaceActivity.class);
                startActivity(i);
                break;
        }
    }
}