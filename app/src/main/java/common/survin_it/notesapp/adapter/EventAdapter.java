package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.Events;

public class EventAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<DailyEvents> list;

    public EventAdapter(Context context, List<DailyEvents> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new EventAdapter.ViewHolder(inflateLayout(R.layout.event_list));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvEventTime,tvEventDate;
        LinearLayout ll_main;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEventTime = itemView.findViewById(R.id.tv_event_time);
            tvEventDate = itemView.findViewById(R.id.tv_event_name);
            ll_main = itemView.findViewById(R.id.ll_main);
        }

        @Override
        public void setData(int position) {

            DailyEvents description = list.get(position);
            tvEventTime.setText(description.getTime());
            tvEventDate.setText(description.getTitle());

            ll_main.setTag(position);
            ll_main.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}