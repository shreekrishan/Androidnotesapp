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
import common.survin_it.notesapp.model.EventYears;
import common.survin_it.notesapp.model.Events;

public class MyEventsYears extends AppBaseRecycleAdapter {

    Context context;
    List<EventYears> list;

    public MyEventsYears(Context context, List<EventYears> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new MyEventsYears.ViewHolder(inflateLayout(R.layout.item_event_list_month));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvYear;
        RecyclerView rv_particular_event;


        public ViewHolder(View itemView) {
            super(itemView);
            tvYear = itemView.findViewById(R.id.tv_event_year);
        }

        @Override
        public void setData(int position) {

            EventYears calender = list.get(position);
            tvYear.setText(calender.getYear());

        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}