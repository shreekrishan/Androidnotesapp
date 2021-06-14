package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.Calender;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Events;

public class CalenderItemAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<Calender> list;

    public CalenderItemAdapter(Context context, List<Calender> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new CalenderItemAdapter.ViewHolder(inflateLayout(R.layout.calender_event_list));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvDate,tvDay,tvMonth;
        LinearLayout ll_calender;
        RecyclerView rv_particular_event;

        //
        String selectedDate = "",eventDocId="",day="",date="",month="";

        List<String> allDocId = new ArrayList<>();
        List<DailyEvents> eventList = new ArrayList<>();
       // RecyclerView rvEvents;
        private EventAdapter eventAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvDate = itemView.findViewById(R.id.tv_event_time);
            tvMonth = itemView.findViewById(R.id.tv_month);
            ll_calender = itemView.findViewById(R.id.ll_calander);
            rv_particular_event = itemView.findViewById(R.id.rv_particular_event);
        }

        @Override
        public void setData(int position) {

            Calender calender = list.get(position);
            tvDate.setText(calender.getDate());
            tvDay.setText(calender.getDay());
            tvMonth.setText(calender.getMonth());

            ll_calender.setTag(position);
            ll_calender.setOnClickListener(this);

            if (tvDate.getText().toString().equals("28")){
                Common.db.collection("event").whereEqualTo("start_date","28-04-2021")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                boolean documentExists;
                                eventList.clear();
                                if (task.isSuccessful()){
                                    documentExists = !task.getResult().isEmpty();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        allDocId.add(document.getId());

                                    }
                                    eventList.clear();
                                    for (int i=0;i<allDocId.size();i++){
                                        eventList.clear();
                                        Common.db.collection("event").document(allDocId.get(i))
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        Events events = task.getResult().toObject(Events.class);

                                                        DailyEvents dailyEvents = new DailyEvents();
                                                        dailyEvents.setTime(events.getStart_time());
                                                        dailyEvents.setTitle(events.getSubject_title());

                                                        eventList.add(dailyEvents);

                                                    }
                                                });
                                    }


                                }
                            }
                        });

                eventAdapter = new EventAdapter(getContext(), eventList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rv_particular_event.setLayoutManager(linearLayoutManager);
                rv_particular_event.setAdapter(eventAdapter);
                rv_particular_event.setNestedScrollingEnabled(true);
            }


        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}