package common.survin_it.notesapp.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.CalenderItemAdapter;
import common.survin_it.notesapp.adapter.EventAdapter;
import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.Calender;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.Events;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class EventListByCalenderActivity extends AppCompatActivity {

    FloatingActionButton addButton,searchButton,menuButton;
    ImageView ivCenterIcon;

     RecyclerView rvDate;
     List<Calender> dateList = new ArrayList<>();
    CalenderItemAdapter calenderItemAdapter;

    //
    String selectedDate = "",eventDocId="",day="",date="",month="";

    List<String> allDocId = new ArrayList<>();
    List<DailyEvents> eventList = new ArrayList<>();
    RecyclerView rvEvents;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_by_calender);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        addButton = findViewById(R.id.add_button);
        searchButton = findViewById(R.id.btn_search);
        menuButton = findViewById(R.id.btn_menu);
        ivCenterIcon = findViewById(R.id.iv_icon);
        ivCenterIcon.setVisibility(View.GONE);
        rvEvents = findViewById(R.id.rv_events);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventListByCalenderActivity.this, EventDetailsActivity.class);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventListByCalenderActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
            }
        });
       // SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date date = new Date();
        //String dayOfTheWeek = sdf.format(d);
        // get required dates


        for(int i=0;i<15;i++){

            String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
            String day          = (String) DateFormat.format("dd",   date); // 20
            int dateDD = Integer.parseInt(day);
            String monthString  = (String) DateFormat.format("MMMM",  date); // Jun

            if (dateDD + i == 31) {
                String dates = String.valueOf(1);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 32) {
                String dates = String.valueOf(2);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 33) {
                String dates = String.valueOf(3);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 34) {
                String dates = String.valueOf(4);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 35) {
                String dates = String.valueOf(5);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 36) {
                String dates = String.valueOf(6);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 37) {
                String dates = String.valueOf(7);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 38) {
                String dates = String.valueOf(8);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 39) {
                String dates = String.valueOf(9);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 40) {
                String dates = String.valueOf(10);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 41) {
                String dates = String.valueOf(11);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 42) {
                String dates = String.valueOf(12);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 43) {
                String dates = String.valueOf(13);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 44) {
                String dates = String.valueOf(14);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }
            else if (dateDD + i == 45) {
                String dates = String.valueOf(15);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            } else {
                String dates = String.valueOf(dateDD + i);
                Calender calender = new Calender();
                calender.setDate(dates);
                calender.setDay(dayOfTheWeek);
                calender.setMonth(monthString);

                dateList.add(calender);
            }

        }

//        Calender calender = new Calender();
//        calender.setDate(day);
//        calender.setDay(dayOfTheWeek);
//        calender.setMonth(monthString);

//        ArrayList<Calender> calenderData = new ArrayList<>();
//        calenderData.add(calender);
//        addData(calenderData);

        //Toast.makeText(EventListByCalenderActivity.this,dateList.get(0).getMonth(),Toast.LENGTH_SHORT).show();

        rvDate = findViewById(R.id.rv_date);
        initializeRecyclerView();


    }

    private void addData(List<Calender> levelList) {
        if (levelList != null) {
            this.dateList.addAll(levelList);
        }
        if (calenderItemAdapter != null) {
            calenderItemAdapter.notifyDataSetChanged();
            // updateNoDataView();
        }
    }

    private void initializeRecyclerView() {
        calenderItemAdapter = new CalenderItemAdapter(this, dateList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDate.setLayoutManager(linearLayoutManager);
        rvDate.setAdapter(calenderItemAdapter);
        rvDate.setNestedScrollingEnabled(true);

        new ItemClickSupport(rvDate).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                switch (v.getId()) {
                    case R.id.ll_calander:
                        // get data from firebase

                        break;
                }
            }
        });
    }
}