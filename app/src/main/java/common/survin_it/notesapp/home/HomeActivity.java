package common.survin_it.notesapp.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.CalendarActivity;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.EventAdapter;
import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.Events;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton addButton,searchButton,menuButton;
    ImageView ivCenterIcon;
    TextView userName, currentDate,tvWeek;
    LinearLayout tvEvent;

    LinearLayout ll_bottom;
    private BottomSheetBehavior mBottomSheetBehavior;


    String user_name;

    TextView tvEventTime,tvEventName;

    String selectedDate = "",eventDocId="",day="",date="",month="";

    List<String> allDocId = new ArrayList<>();
    List<DailyEvents> eventList = new ArrayList<>();
    RecyclerView rvEvents;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        tvEvent = findViewById(R.id.tv_meetings);

        tvEventTime = findViewById(R.id.tv_event_time);
        tvEventName = findViewById(R.id.tv_event_name);
        rvEvents = findViewById(R.id.rv_events);

        tvEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,EventListByCalenderActivity.class);
                startActivity(intent);
            }
        });

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

        addButton = findViewById(R.id.add_button);
        searchButton = findViewById(R.id.btn_search);
        menuButton = findViewById(R.id.btn_menu);
        ivCenterIcon = findViewById(R.id.iv_icon);
        ivCenterIcon.setVisibility(View.GONE);

        addButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);

        String formate = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(formate);
        String myDate = inputFormat.format(d);

        // get data from firebase
        Common.db.collection("event").whereEqualTo("start_date",myDate)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean documentExists;
                        if (task.isSuccessful()){
                            documentExists = !task.getResult().isEmpty();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                eventDocId = document.getId();
                                allDocId.add(document.getId());
                                //new introduce

                            }
                            for (int i=0;i<allDocId.size();i++){
                                Common.db.collection("event").document(allDocId.get(i))
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                Events events = task.getResult().toObject(Events.class);

                                             //   Toast.makeText(HomeActivity.this,events.getSubject(),Toast.LENGTH_SHORT).show();
                                                //   String one = events.getDescriptionEvent().getDescription4();
//                                                tvEventName.setText(events.getSubject());
//                                                tvEventTime.setText(events.getStartTime());

                                                DailyEvents dailyEvents = new DailyEvents();
                                                dailyEvents.setTime(events.getStart_time());
                                                dailyEvents.setTitle(events.getSubject_title());
                                                eventList.add(dailyEvents);

                                                eventAdapter = new EventAdapter(getApplication(), eventList);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                                                rvEvents.setLayoutManager(linearLayoutManager);
                                                rvEvents.setAdapter(eventAdapter);
                                                rvEvents.setNestedScrollingEnabled(true);
                                                new ItemClickSupport(rvEvents).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                                        switch (v.getId()) {
                                                            case R.id.ll_main:
                                                                Intent intent = new Intent(HomeActivity.this,EventListByCalenderActivity.class);
                                                                startActivity(intent);
                                                                break;
                                                        }
                                                    }
                                                });

                                            }
                                        });
                            }


                        }
                    }
                });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_button:
                Intent intent = new Intent(HomeActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_search:
                Intent intent2 = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_menu:
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                break;
        } }

}