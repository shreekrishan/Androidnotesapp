package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.EventsModel;

public class Adapter_MySpace_events_details extends AppBaseRecycleAdapter {

    Context context;
    List<EventsModel> list;

    public Adapter_MySpace_events_details(Context context, List<EventsModel> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new Adapter_MySpace_events_details.ViewHolder(inflateLayout(R.layout.event_list));
    }

    @Override
    public int getDataCount() {
        return 10;

       // return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tv_event_time;
        TextView tv_event_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_event_time = itemView.findViewById(R.id.tv_event_time);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);
        }

        @Override
        public void setData(int position) {

          /*  EventsModel eventsModel = list.get(position);
            tv_event_time.setText(eventsModel.getEventTitle());
            tv_event_name.setText(eventsModel.getEventDetails());
*/
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}