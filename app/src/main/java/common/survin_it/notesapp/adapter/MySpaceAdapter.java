package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.DailyEvents;

public class MySpaceAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<DailyEvents> list;

    public MySpaceAdapter(Context context, List<DailyEvents> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new MySpaceAdapter.ViewHolder(inflateLayout(R.layout.item_event_list));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvEventTime,tvEventDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEventTime = itemView.findViewById(R.id.tv_date_month);
            tvEventDate = itemView.findViewById(R.id.tv_event_name);
        }

        @Override
        public void setData(int position) {

            DailyEvents description = list.get(position);
            tvEventTime.setText(description.getTime());
            tvEventDate.setText(description.getTitle());

        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}
