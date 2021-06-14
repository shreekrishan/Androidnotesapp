package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.EventsModel;
import common.survin_it.notesapp.model.My_space_events;
import common.survin_it.notesapp.utilities.ItemClickSupport;

public class Adapter_MySpace_events extends AppBaseRecycleAdapter {

    Context context;
    List<My_space_events> list;

    public Adapter_MySpace_events(Context context, List<My_space_events> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new Adapter_MySpace_events.ViewHolder(inflateLayout(R.layout.item_eventlist));
    }

    @Override
    public int getDataCount() {
         return 10;

        //return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tv_event_date;
        RecyclerView rv_eventdetails;
        Adapter_MySpace_events adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_event_date = itemView.findViewById(R.id.tv_event_date);
            rv_eventdetails = itemView.findViewById(R.id.rv_eventdetails);
        }

        @Override
        public void setData(int position) {

      /*      My_space_events my_space_events = list.get(position);
            tv_event_date.setText(my_space_events.getEvent_date());
            rv_eventdetails.setAdapter(adapter);
            ItemClickSupport.addTo(rv_eventdetails).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    //performChildItemClick((Integer) recycler_view.getTag(), position, v);
                }
            });*/
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}