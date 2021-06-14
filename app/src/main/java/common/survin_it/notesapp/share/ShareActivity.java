package common.survin_it.notesapp.share;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Users_adapter;
import common.survin_it.notesapp.model.Users;
import common.survin_it.notesapp.settings.Settings_AdminSubscriptionrActivity;

public class ShareActivity extends AppCompatActivity {

    RecyclerView rv_user_list;

    TextView tvSharedName;
    EditText etSearchText;
    Button inviteButton;
    ImageView ivSearch;

    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    private FirestoreRecyclerAdapter adapterSearch;
    Query query;
    FirestoreRecyclerOptions<Users> option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        rv_user_list = findViewById(R.id.rv_shared_user);
        tvSharedName = findViewById(R.id.shared_names);
        etSearchText = findViewById(R.id.et_search_text);
        inviteButton = findViewById(R.id.invite_button);
        ivSearch = findViewById(R.id.iv_search);

        firebaseFirestore = FirebaseFirestore.getInstance();

        //get data from firebase
        query = firebaseFirestore.collection("users");
        option = new FirestoreRecyclerOptions.Builder<Users>()
                .setQuery(query,Users.class)
                .build();

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = firebaseFirestore.collection("users")
                        .whereEqualTo("name",etSearchText.getText().toString());
                option = new FirestoreRecyclerOptions.Builder<Users>()
                        .setQuery(query,Users.class)
                        .build();

                adapterSearch = new FirestoreRecyclerAdapter<Users, UserViewHolder>(option) {
                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user,parent,false);
                        return new UserViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Users model) {
                        holder.tvUserName.setText(model.getName().toString());
                        //  Toast.makeText(Settings_AdminApprovalcenterActivity.this,model.getName(),Toast.LENGTH_SHORT).show();
                    }
                };
                rv_user_list.setHasFixedSize(false);
                rv_user_list.setLayoutManager(new LinearLayoutManager(ShareActivity.this,RecyclerView.VERTICAL,false));
                rv_user_list.setAdapter(adapterSearch);
            }
        });

        adapter = new FirestoreRecyclerAdapter<Users, UserViewHolder>(option) {
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user,parent,false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Users model) {
                holder.tvUserName.setText(model.getName().toString());
                //  Toast.makeText(Settings_AdminApprovalcenterActivity.this,model.getName(),Toast.LENGTH_SHORT).show();
            }
        };
        rv_user_list.setHasFixedSize(false);
        rv_user_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rv_user_list.setAdapter(adapter);
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        ImageView ivTick;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_names);
            ivTick = itemView.findViewById(R.id.imageView2);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        //adapterSearch.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        //adapterSearch.stopListening();
    }
}