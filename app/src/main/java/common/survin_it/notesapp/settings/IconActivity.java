package common.survin_it.notesapp.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.IconItemAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.DescriptionDocument;
import common.survin_it.notesapp.model.Icon;
import common.survin_it.notesapp.model.IconDocument;

public class IconActivity extends AppCompatActivity {

    RecyclerView rvIcon;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private FirestoreRecyclerAdapter adapter;
    Query query;

    private IconItemAdapter iconItemAdapter;
    List<Icon> iconList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        rvIcon = findViewById(R.id.rv_icon);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference applicationsRef = rootRef.collection("icons");
        DocumentReference applicationIdRef = applicationsRef.document("app_icons");
        applicationIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    //description = (List<Map<String, Object>>) document.get("description");

                    iconList = document.toObject(IconDocument.class).icon;
                    iconItemAdapter = new IconItemAdapter(this, iconList);
                    GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 5);
                    rvIcon.setLayoutManager(linearLayoutManager);
                    rvIcon.setAdapter(iconItemAdapter);
                    rvIcon.setNestedScrollingEnabled(true);

                    // descriptionList.addAll(description.toArray());
                    // Toast.makeText(EventDetailsActivity.this,description.toArray().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}