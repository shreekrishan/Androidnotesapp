package common.survin_it.notesapp.all_spaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.Dummy.Dummy;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.CalenderItemAdapter;
import common.survin_it.notesapp.adapter.EventAdapter;
import common.survin_it.notesapp.adapter.MyEventsYears;
import common.survin_it.notesapp.adapter.MySpaceAdapter;
import common.survin_it.notesapp.adapter.Users_adapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.EventListByCalenderActivity;
import common.survin_it.notesapp.home.HomeActivity;
import common.survin_it.notesapp.model.Calender;
import common.survin_it.notesapp.model.CalenderManagement;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.EventYears;
import common.survin_it.notesapp.model.Events;
import common.survin_it.notesapp.model.MySpace;
import common.survin_it.notesapp.model.Users;
import common.survin_it.notesapp.settings.Settings_AdminApprovalcenterActivity;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class AllEventsListActivity extends AppCompatActivity {

    TextView tvTags,tvMainTags;

    TextView tvPanchangaYear,tvDateMonth,tvPanchangaName;
    String eventDocId="";

    //local events
    List<String> allDocId = new ArrayList<>();
    List<DailyEvents> eventList = new ArrayList<>();
    RecyclerView rvEvents;
    private MySpaceAdapter eventAdapter;

    String tagss="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events_list);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        rvEvents = findViewById(R.id.rv_event_year);
        tvTags = findViewById(R.id.tv_tags);
        tvMainTags = findViewById(R.id.tv_main_tag);


        tvDateMonth = findViewById(R.id.tv_date_month);
        tvPanchangaName = findViewById(R.id.tv_event_name);
        tvPanchangaYear = findViewById(R.id.tv_event_year);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        String dayOfTheWeek2 = sdf2.format(d);
        tvPanchangaYear.setText(dayOfTheWeek +" "+dayOfTheWeek2);

        String formate = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(formate);
        String myDate = inputFormat.format(d);

        if (Common.spaceType.equals("1")){
            // get data from firebase
            tvTags.setText("Panchanga");
            tvMainTags.setText("My Calendar");
            tvDateMonth.setVisibility(View.VISIBLE);
            tvPanchangaName.setVisibility(View.VISIBLE);
            rvEvents.setVisibility(View.GONE);
            Common.db.collection("event").whereEqualTo("start_date",myDate)
                    .whereEqualTo("tags","Panchanga")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            boolean documentExists;
                            if (task.isSuccessful()){
                                documentExists = !task.getResult().isEmpty();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    eventDocId = document.getId();
                                    Common.db.collection("event").document(eventDocId)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    Events events = task.getResult().toObject(Events.class);

                                                    SimpleDateFormat sdf = new SimpleDateFormat("MMM");
                                                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                                                    Date d = new Date();
                                                    String dayOfTheWeek = sdf.format(d);
                                                    String dayOfTheWeek2 = sdf2.format(d);
                                                    tvDateMonth.setText(dayOfTheWeek +" "+dayOfTheWeek2);

                                                    // tvDateMonth.setText(events.getPanchanga_data());
                                                    tvPanchangaName.setText(events.getSubject_title());

                                                }
                                            });
                                }



                            }
                        }
                    });
        }
        else if(Common.spaceType.equals("2")){
            //local events
            // get data from firebase
            tvTags.setText("Local Events");
            tvMainTags.setText("My Calendar");
            tvDateMonth.setVisibility(View.GONE);
            tvPanchangaName.setVisibility(View.GONE);
            rvEvents.setVisibility(View.VISIBLE);
            Common.db.collection("event")
//                    .whereEqualTo("start_date",myDate)
//                    .whereEqualTo("tags","Local event")
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
                                                    dailyEvents.setTime(events.getStart_date());
                                                    dailyEvents.setTitle(events.getSubject_title());
                                                    eventList.add(dailyEvents);

                                                    eventAdapter = new MySpaceAdapter(getApplication(), eventList);
                                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                                                    rvEvents.setLayoutManager(linearLayoutManager);
                                                    rvEvents.setAdapter(eventAdapter);
                                                    rvEvents.setNestedScrollingEnabled(true);
                                                }
                                            });
                                }


                            }
                        }
                    });
        }
        else if(Common.spaceType.equals("3")){
            tvMainTags.setText("My Events");

            if(Dummy.btn_all==1){
                tagss+="All";
            }
            else if(Dummy.btn_sports==1){
                tagss+=" Sports";
            }
            if(Dummy.btn_music==1){
                tagss+=" Music";
            }
            if(Dummy.btn_holiday==1){
                tagss+=" Holiday";
            }
            if(Dummy.btn_event==1){
                tagss+=" Events";
            }

            tvTags.setText(tagss);

            //All events
            // get data from firebase
            tvDateMonth.setVisibility(View.GONE);
            tvPanchangaName.setVisibility(View.GONE);
            rvEvents.setVisibility(View.VISIBLE);
            Common.db.collection("event").whereNotEqualTo("tags","Panchanga")
                    .whereEqualTo("tags","Local event")
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
                                                    dailyEvents.setTime(events.getStart_date());
                                                    dailyEvents.setTitle(events.getSubject_title());
                                                    eventList.add(dailyEvents);

                                                    eventAdapter = new MySpaceAdapter(getApplication(), eventList);
                                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                                                    rvEvents.setLayoutManager(linearLayoutManager);
                                                    rvEvents.setAdapter(eventAdapter);
                                                    rvEvents.setNestedScrollingEnabled(true);
                                                }
                                            });
                                }


                            }
                        }
                    });
        }


//        if(Dummy.btn_all == 1){
//            tagList.add("All");
//        }
//        else if(Dummy.btn_sports == 1){
//            tagList.add(","+"Sports");
//        }
//        else if(Dummy.btn_event == 1){
//            tagList.add(","+"Events");
//        }
//
//        for (int i = 0 ;i<tagList.size();i++){
//            allTags = allTags + tagList.get(i);
//        }
//
//        //tvTags.setText(allTags);
//
//        //add year data
//        EventYears eventYears = new EventYears();
//        eventYears.setYear("2021 May");
//        yearList.add(eventYears);
//        EventYears eventYears2 = new EventYears();
//        eventYears.setYear("2021 June");
//        yearList.add(eventYears2);
//        EventYears eventYears3 = new EventYears();
//        eventYears.setYear("2021 July");
//        yearList.add(eventYears3);
//        EventYears eventYears4 = new EventYears();
//        eventYears.setYear("2021 August");
//        yearList.add(eventYears4);
//        EventYears eventYears5 = new EventYears();
//        eventYears.setYear("2021 September");
//        yearList.add(eventYears5);
//        EventYears eventYears6 = new EventYears();
//        eventYears.setYear("2021 Octomber");
//        yearList.add(eventYears6);
//        EventYears eventYears7 = new EventYears();
//        eventYears.setYear("2021 November");
//        yearList.add(eventYears7);
//        EventYears eventYears8 = new EventYears();
//        eventYears.setYear("2021 December");
//        yearList.add(eventYears8);
//        EventYears eventYears9 = new EventYears();
//        eventYears.setYear("2022 January");
//        yearList.add(eventYears9);
//        EventYears eventYears10 = new EventYears();
//        eventYears.setYear("2022 Februry");
//        yearList.add(eventYears10);
//        EventYears eventYears11 = new EventYears();
//        eventYears.setYear("2022 March");
//        yearList.add(eventYears11);
//        EventYears eventYears12 = new EventYears();
//        eventYears.setYear("2022 April");
//        yearList.add(eventYears12);
//
//        rvYears = findViewById(R.id.rv_event_year);
//        initializeRecyclerView();
//
//        //get data from firebase
//        query = firebaseFirestore.collection("users").document(firebaseAuth.getUid()).collection("new_space");
//        FirestoreRecyclerOptions<MySpace> options = new FirestoreRecyclerOptions.Builder<MySpace>()
//                .setQuery(query,MySpace.class)
//                .build();
//
//        adapter = new FirestoreRecyclerAdapter<MySpace, MySpaceViewHolder>(options) {
//            @NonNull
//            @Override
//            public MySpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_list_month,parent,false);
//                return new MySpaceViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull MySpaceViewHolder holder, int position, @NonNull MySpace model) {
//                holder.tvMonthYear.setText(model.getStart_date());
//              //  holder.tvUserNumber.setText(model.getMobile());
//                //  Toast.makeText(Settings_AdminApprovalcenterActivity.this,model.getName(),Toast.LENGTH_SHORT).show();
//            }
//        };
//        rv_month_year.setHasFixedSize(false);
//        rv_month_year.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//        rv_month_year.setAdapter(adapter);
    }
//    private class MySpaceViewHolder extends RecyclerView.ViewHolder {
//        TextView tvMonthYear,tvUserNumber,tvApprove;
//        public MySpaceViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvMonthYear = itemView.findViewById(R.id.tv_event_year);
////            tvUserNumber = itemView.findViewById(R.id.tv_user_number);
////            tvApprove = itemView.findViewById(R.id.tv_approve);
//        }
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    private void initializeRecyclerView() {
//        myEventsYears = new MyEventsYears(this, yearList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rvYears.setLayoutManager(linearLayoutManager);
//        rvYears.setAdapter(myEventsYears);
//        rvYears.setNestedScrollingEnabled(true);
//
////        new ItemClickSupport(rvDate).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
////            @Override
////            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
////                switch (v.getId()) {
////                    case R.id.ll_calander:
////                        // get data from firebase
////
////                        break;
////                }
////            }
////        });
//    }
}