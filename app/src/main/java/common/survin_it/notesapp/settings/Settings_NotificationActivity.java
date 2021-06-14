package common.survin_it.notesapp.settings;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.EventAdapter;
import common.survin_it.notesapp.adapter.NotificationAdapter;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.EventListByCalenderActivity;
import common.survin_it.notesapp.home.HomeActivity;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Events;
import common.survin_it.notesapp.model.Notifications;
import common.survin_it.notesapp.model.UserNotification;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class Settings_NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross, iv_icon;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton btn_menu, add_button;
 //   TextView tv_changenumber;

    List<String> allDocId = new ArrayList<>();
    List<UserNotification> notificationList = new ArrayList<>();
    RecyclerView rvNotifications;
    private NotificationAdapter notificationAdapter;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notification);
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
        //tv_changenumber = findViewById(R.id.tv_changenumber);
        iv_icon = findViewById(R.id.iv_icon);
        add_button = findViewById(R.id.add_button);
        iv_cross.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
//        tv_changenumber.setOnClickListener(this);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);


        rvNotifications = findViewById(R.id.rv_notification);
        date=new Date();

        // get data from firebase
        Common.db.collection("notifications").whereEqualTo("user_id",Common.firebaseAuth.getUid())
                .whereEqualTo("deleted","false")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean documentExists;
                        if (task.isSuccessful()){
                            documentExists = !task.getResult().isEmpty();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                allDocId.add(document.getId());
                                //new introduce

                            }
                            for (int i=0;i<allDocId.size();i++){
                                Common.db.collection("notifications").document(allDocId.get(i))
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                Notifications notifications = task.getResult().toObject(Notifications.class);

                                                //   Toast.makeText(HomeActivity.this,events.getSubject(),Toast.LENGTH_SHORT).show();
                                                //   String one = events.getDescriptionEvent().getDescription4();
//                                                tvEventName.setText(events.getSubject());
//                                                tvEventTime.setText(events.getStartTime());

                                                UserNotification userNotification = new UserNotification();
                                                userNotification.setDescription(notifications.getDescription());
                                                userNotification.setTitle(notifications.getTitle());
                                                notificationList.add(userNotification);

                                                notificationAdapter = new NotificationAdapter(getApplication(), notificationList);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                                                rvNotifications.setLayoutManager(linearLayoutManager);
                                                rvNotifications.setAdapter(notificationAdapter);
                                                rvNotifications.setNestedScrollingEnabled(true);
                                                new ItemClickSupport(rvNotifications).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                                        switch (v.getId()) {
                                                            case R.id.iv_cross:
                                                                Common.db.collection("notifications")
                                                                        .document(allDocId.get(position))
                                                                        .update("deleted","true",
                                                                                "deleted_at",date)
                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(Settings_NotificationActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                                                recreate();
                                                                            }
                                                                        })
                                                                        .addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(Exception e) {
                                                                                Toast.makeText(Settings_NotificationActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });
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

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_NotificationActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
//            case R.id.tv_changenumber:
//                Intent inent = new Intent(Settings_NotificationActivity.this, Settings_ChangeNumberActivity.class);
//                startActivity(inent);
//                break;
        }
    }

}