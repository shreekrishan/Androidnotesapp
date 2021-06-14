package common.survin_it.notesapp.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Alert_adapter;
import common.survin_it.notesapp.adapter.Tags_adapter;
import common.survin_it.notesapp.adapter.Users_adapter;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.model.Users;

public class Settings_AdminApprovalcenterActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_cross;
    FloatingActionButton btn_menu;
    ImageView  iv_icon;
    LinearLayout ll_localevents, ll_localeventsdetail;
    FloatingActionButton add_button;
    TextView tv_changenumber, tv_location, tv_deleteaccount;

    RecyclerView rv_user_list;
    private Users_adapter users_adapter;
    List<Users> userList = new ArrayList<>();

    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin_approval_center);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        iv_cross=findViewById(R.id.iv_cross);
        btn_menu=findViewById(R.id.btn_menu);
        add_button=findViewById(R.id.add_button);
        iv_icon=findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        btn_menu.setOnClickListener(this);

        rv_user_list = findViewById(R.id.rv_user_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //get data from firebase
        query = firebaseFirestore.collection("users");
        FirestoreRecyclerOptions<Users> options = new FirestoreRecyclerOptions.Builder<Users>()
                .setQuery(query,Users.class)
                .build();



        adapter = new FirestoreRecyclerAdapter<Users, UserViewHolder>(options) {
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list,parent,false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Users model) {
                holder.tvUserName.setText(model.getName());
                holder.tvUserNumber.setText(model.getMobile());
              //  Toast.makeText(Settings_AdminApprovalcenterActivity.this,model.getName(),Toast.LENGTH_SHORT).show();
            }
        };
        rv_user_list.setHasFixedSize(false);
        rv_user_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rv_user_list.setAdapter(adapter);


        //initializeRecyclerView();
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);
  }

    private void initializeRecyclerView() {
        users_adapter = new Users_adapter(this, userList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_user_list.setLayoutManager(linearLayoutManager2);
        rv_user_list.setAdapter(users_adapter);
        rv_user_list.setNestedScrollingEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_AdminApprovalcenterActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;

        } }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName,tvUserNumber,tvApprove;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvUserNumber = itemView.findViewById(R.id.tv_user_number);
            tvApprove = itemView.findViewById(R.id.tv_approve);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}