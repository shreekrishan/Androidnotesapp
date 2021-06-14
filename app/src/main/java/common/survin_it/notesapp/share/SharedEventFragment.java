package common.survin_it.notesapp.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.EventAdapter;
import common.survin_it.notesapp.adapter.SharedEventAdapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.EventListByCalenderActivity;
import common.survin_it.notesapp.home.HomeActivity;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Events;
import common.survin_it.notesapp.model.SharedEvent;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class SharedEventFragment extends Fragment {
    //    private FirebaseFirestore firebaseFirestore;
//    private FirestoreRecyclerAdapter adapter;
//    Query query;

    List<String> allDocId = new ArrayList<>();
    List<SharedEvent> eventList = new ArrayList<>();
    RecyclerView rvSharedEvent;
    private SharedEventAdapter sharedEventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shared_event, container, false);

        rvSharedEvent = view.findViewById(R.id.rv_shared_event);
//        firebaseFirestore = FirebaseFirestore.getInstance();
//
//        //get data from firebase
//        query = firebaseFirestore.collection("shared_event");
//        FirestoreRecyclerOptions<SharedEvent> options = new FirestoreRecyclerOptions.Builder<SharedEvent>()
//                .setQuery(query,SharedEvent.class)
//                .build();

//        adapter = new FirestoreRecyclerAdapter<SharedEvent, SharedEventViewHolder>(options) {
//            @NonNull
//            @Override
//            public SharedEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shared_event_item,parent,false);
//                return new SharedEventViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull SharedEventViewHolder holder, int position, @NonNull SharedEvent model) {
//                holder.tvTitle.setText(model.getTitle());
//                holder.tvName.setText(model.getName());
//                holder.tvTime.setText(model.getDate()+" , "+model.getTime());
//
//                holder.tvAdd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        holder.tvAdd.setVisibility(View.GONE);
//
//                        holder.ivCross.setVisibility(View.VISIBLE);
//                        holder.ivTick.setVisibility(View.VISIBLE);
//
//                    }
//                });
//                holder.ivCross.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        holder.ivCross.setVisibility(View.GONE);
//
//                        holder.tvAdd.setVisibility(View.VISIBLE);
//                        holder.ivTick.setVisibility(View.GONE);
//
//                    }
//                });
//
//               // Toast.makeText(getActivity(),model.getTitle().toString(),Toast.LENGTH_SHORT).show();
//            }
//        };
//        rvSharedEvent.setHasFixedSize(false);
//        rvSharedEvent.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
//        rvSharedEvent.setAdapter(adapter);

        Common.db.collection("shared").document(Common.firebaseAuth.getUid()).collection("events")
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

                                                SharedEvent sharedEvent = new SharedEvent();
                                                sharedEvent.setName(events.getAdministrated_by());
                                                sharedEvent.setTitle(events.getSubject_title());
                                                sharedEvent.setDate(events.getStart_date());
                                                sharedEvent.setTime(events.getStart_time());
                                                eventList.add(sharedEvent);

                                                sharedEventAdapter = new SharedEventAdapter(getActivity(), eventList);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                                                rvSharedEvent.setLayoutManager(linearLayoutManager);
                                                rvSharedEvent.setAdapter(sharedEventAdapter);
                                                rvSharedEvent.setNestedScrollingEnabled(true);

                                            }
                                        });
                            }

                        }
                    }
                });

        return view;
    }

//    private class SharedEventViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle,tvName,tvTime;
//        TextView tvAdd;
//        ImageView ivTick,ivCross;
//        public SharedEventViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvTitle = itemView.findViewById(R.id.tv_title);
//            tvName = itemView.findViewById(R.id.tv_name);
//            tvTime = itemView.findViewById(R.id.tv_time);
//            tvAdd = itemView.findViewById(R.id.tv_add);
//            ivTick = itemView.findViewById(R.id.tick);
//            ivCross = itemView.findViewById(R.id.cross);
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}