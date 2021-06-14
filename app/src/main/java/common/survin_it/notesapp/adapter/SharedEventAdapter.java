package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.DailyEvents;
import common.survin_it.notesapp.model.SharedEvent;

public class SharedEventAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<SharedEvent> list;

    public SharedEventAdapter(Context context, List<SharedEvent> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new SharedEventAdapter.ViewHolder(inflateLayout(R.layout.shared_event_item));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvTitle,tvName,tvTime;
        TextView tvAdd;
        ImageView ivTick,ivCross;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvAdd = itemView.findViewById(R.id.tv_add);
            ivTick = itemView.findViewById(R.id.tick);
            ivCross = itemView.findViewById(R.id.cross);
        }

        @Override
        public void setData(int position) {

            SharedEvent description = list.get(position);
            tvTitle.setText(description.getTitle());
            tvName.setText(description.getName());
            tvTime.setText(description.getTime());

        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}