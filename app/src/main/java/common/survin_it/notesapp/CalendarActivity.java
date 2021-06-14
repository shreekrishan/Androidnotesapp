package common.survin_it.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.timessquare.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.BottomSheetDialog;
import common.survin_it.notesapp.home.BottomSheet_date_event_calendar;
import common.survin_it.notesapp.home.EventDetailsActivity;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.DescriptionDocument;
import common.survin_it.notesapp.model.Years;
import common.survin_it.notesapp.model.YearsDocuments;
import common.survin_it.notesapp.register.RegistrationActivity_1;
import common.survin_it.notesapp.register.SelectLocationActivity;

public class CalendarActivity extends AppCompatActivity {

    NumberPicker number_picker;
    int newValue,y,m,d;
    String[] pickerVals = new String[] {"2019", "2020", "2021", "2022", "2023"};

    Calendar nextYear;
    SimpleDateFormat sdf;
    String currentDateandTime;
    Date dt;
    String[] data;

    // firebase integrate
    private FirebaseFirestore db;
    List<String> years = new ArrayList<>();

    List<Years> yearList = new ArrayList<>();

    FloatingActionButton btnMenu;
    CalendarPickerView calendar;

    public static final int REQUEST_DATE_CODE = 777;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        btnMenu =  findViewById(R.id.btn_menu);
        imageView =  findViewById(R.id.iv_icon);
        imageView.setVisibility(View.GONE);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet_date_event_calendar bottomSheet = new BottomSheet_date_event_calendar();
                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
            }
        });
        db = FirebaseFirestore.getInstance();
        Calendar cl = Calendar.getInstance();
        y = cl.get(Calendar.YEAR);
        m = cl.get(Calendar.MONTH);
        d = cl.get(Calendar.DATE);

        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

         sdf = new SimpleDateFormat("yyyy/MM/dd");
         y = y - 1900;
         currentDateandTime= sdf.format(new Date( y,m,d));
         dt=null;
        try{
            dt = sdf.parse(currentDateandTime);
            //myDate.setTime(dt);
        }catch (ParseException e){
            e.printStackTrace();
        }
        calendar.init(dt, nextYear.getTime()).withSelectedDate(dt);

        number_picker =findViewById(R.id.et_town);
       // number_picker.setValue(2021);
//        number_picker.setMinValue(0);
//        number_picker.setMaxValue(4);
//        number_picker.setDisplayedValues(pickerVals);

        //open another activity on date click
//        Intent intent = new Intent();
//        Calendar myDate = Calendar.getInstance();
//        myDate.set(Calendar.YEAR, 2021);
//        myDate.set(Calendar.MONTH, 1);
//        myDate.set(Calendar.DAY_OF_MONTH, 1);
//
//        intent.putExtra("myDateKey", myDate.getTimeInMillis());
//        setResult(RESULT_OK, intent);



//        Intent i = new Intent(CalendarActivity.this, EventDetailsActivity.class);
//        startActivityForResult(i, REQUEST_DATE_CODE);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String formate = "dd-MM-yyyy";
                SimpleDateFormat inputFormat = new SimpleDateFormat(formate);
                String myDate = inputFormat.format(date);

                // get required dates
                String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
                String day          = (String) DateFormat.format("dd",   date); // 20
                String monthString  = (String) DateFormat.format("MMMM",  date); // Jun

                // check in firebase whether event exist or not on selected date
                Common.db.collection("event").whereEqualTo("start_date",myDate)
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
                                    Intent intent = new Intent(CalendarActivity.this,EventDetailsActivity.class);
                                    intent.putExtra("myDateKey", myDate);
                                    intent.putExtra("Day", dayOfTheWeek);
                                    intent.putExtra("Date", day);
                                    intent.putExtra("Month", monthString);
                                    startActivity(intent);

                                }else{
                                    //Toast.makeText(CalendarActivity.this,"no event exist on selected date",Toast.LENGTH_SHORT).show();
                                    BottomSheet_date_event_calendar bottomSheet = new BottomSheet_date_event_calendar();
                                    bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                                }
                            }
                        });
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference applicationsRef = rootRef.collection("all_years");
        DocumentReference applicationIdRef = applicationsRef.document("years");
        applicationIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    //description = (List<Map<String, Object>>) document.get("description");

                    yearList = document.toObject(YearsDocuments.class).years;

                    for(int i=0;i<yearList.size();i++){
                        years.add(yearList.get(i).getTitle());
                    }

                    data = new String[years.size()];
                    data = years.toArray(data);
                    number_picker.setWrapSelectorWheel(true);
                    number_picker.setMinValue(0);
                    number_picker.setMaxValue(data.length - 1);
                    number_picker.setDisplayedValues(data);
                    number_picker.setWrapSelectorWheel(false);

                    number_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                            newValue = Integer.parseInt(data[newVal]);
                            y = newValue - 1900;
                            nextYear = Calendar.getInstance();

                            nextYear.add(Calendar.YEAR, 10);

                            sdf = new SimpleDateFormat("yyyy/MM/dd");
                            currentDateandTime= sdf.format(new Date( y,m,d));
                            dt=null;
                            try{
                                dt = sdf.parse(currentDateandTime);
                            }catch (ParseException e){
                                e.printStackTrace();
                            }
                            calendar.init(dt, nextYear.getTime()).withSelectedDate(dt);
                        }
                    });

                }
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode) {
//            case REQUEST_DATE_CODE:
//                if (resultCode == RESULT_OK && data != null) {
//
//                    Calendar myDate = Calendar.getInstance();
//                    myDate.setTimeInMillis(extras.getLong("myDateKey"));
//
//                    Date date = myDate.getTime();
//                    SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());
//                    Log.d(tag, "Parsed Date: " + simpleDateFormatter.format(date));
//                }
//                break;
//        }
//    }
}