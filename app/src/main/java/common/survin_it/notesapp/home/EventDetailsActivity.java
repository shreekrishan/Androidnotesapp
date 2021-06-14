package common.survin_it.notesapp.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.Events;

public class EventDetailsActivity extends AppCompatActivity {

    RecyclerView rv_description;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private FirestoreRecyclerAdapter adapter;
    Query query;

    TextView tvDayDate,tvMonth,tvDate;
    TextView tvTitle;

    String selectedDate = "",eventDocId="",day="",date="",month="";

    //event items
    //alert items
   // RecyclerView rvEvent;
    private EventItemAdapter eventItemAdapter;
    List<Description> descriptionList = new ArrayList<>();
    Description description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tvTitle = findViewById(R.id.tv_daily_panchanga);
        tvDayDate = findViewById(R.id.tv_day_date);
        tvDate = findViewById(R.id.tvDate);
        tvMonth = findViewById(R.id.tv_month);

        Intent intent = this.getIntent();
        selectedDate = intent.getExtras().getString("myDateKey");
        day = intent.getExtras().getString("Day");
        date = intent.getExtras().getString("Date");
        month = intent.getExtras().getString("Month");


        tvDayDate.setText(day);
        tvMonth.setText(month);
        tvDate.setText(date);

        rv_description = findViewById(R.id.rv_description);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();



        // get data from firebase
        Common.db.collection("event").whereEqualTo("startDate",selectedDate)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean documentExists;
                        if (task.isSuccessful()){
                            documentExists = !task.getResult().isEmpty();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                eventDocId = document.getId();
                                //new introduce
                                Common.db.collection("event").document(eventDocId)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                Events events = task.getResult().toObject(Events.class);
                                             //   String one = events.getDescriptionEvent().getDescription4();
                                                tvTitle.setText(events.getSubject_title());

                                                for (int i=0;i<8;i++){
                                                    if (i==0){
                                                        Description desc = new Description();
                                                        desc.setDesc(events.getDescriptions().getDescription1());
                                                        desc.setDesc1(events.getDescriptions().getDescription2());
                                                        descriptionList.add(desc);
                                                    }
                                                    else if (i==1){
                                                        Description desc1 = new Description();
                                                        desc1.setDesc(events.getDescriptions().getDescription3());
                                                        desc1.setDesc1(events.getDescriptions().getDescription4());
                                                        descriptionList.add(desc1);
                                                    }
                                                    if (i==2){
                                                        Description desc = new Description();
                                                        desc.setDesc(events.getDescriptions().getDescription5());
                                                        desc.setDesc1(events.getDescriptions().getDescription6());
                                                        descriptionList.add(desc);
                                                    }
                                                    else if (i==3){
                                                        Description desc1 = new Description();
                                                        desc1.setDesc(events.getDescriptions().getDescription7());
                                                        desc1.setDesc1(events.getDescriptions().getDescription8());
                                                        descriptionList.add(desc1);
                                                    }
                                                    if (i==4){
                                                        Description desc = new Description();
                                                        desc.setDesc(events.getDescriptions().getDescription9());
                                                        desc.setDesc1(events.getDescriptions().getDescription10());
                                                        descriptionList.add(desc);
                                                    }
                                                    else if (i==5){
                                                        Description desc1 = new Description();
                                                        desc1.setDesc(events.getDescriptions().getDescription11());
                                                        desc1.setDesc1(events.getDescriptions().getDescription12());
                                                        descriptionList.add(desc1);
                                                    }
                                                    if (i==6){
                                                        Description desc = new Description();
                                                        desc.setDesc(events.getDescriptions().getDescription13());
                                                        desc.setDesc1(events.getDescriptions().getDescription14());
                                                        descriptionList.add(desc);
                                                    }
                                                    else if (i==7){
                                                        Description desc1 = new Description();
                                                        desc1.setDesc(events.getDescriptions().getDescription15());
                                                        desc1.setDesc1(events.getDescriptions().getDescription16());
                                                        descriptionList.add(desc1);
                                                    }
//
                                                }
                                               // descriptionList.add(events.getDescriptionEvent());
                                              //  Toast.makeText(EventDetailsActivity.this,one,Toast.LENGTH_SHORT).show();
                                                eventItemAdapter = new EventItemAdapter(getApplication(), descriptionList);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                                                rv_description.setLayoutManager(linearLayoutManager);
                                                rv_description.setAdapter(eventItemAdapter);
                                                rv_description.setNestedScrollingEnabled(true);
                                            }
                                        });

                            }
                        }
                    }
                });
    }

}