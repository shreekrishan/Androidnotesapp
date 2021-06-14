package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.Description;

public class EventItemAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<Description> list;

    public EventItemAdapter(Context context, List<Description> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new EventItemAdapter.ViewHolder(inflateLayout(R.layout.item_event));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvTithiTag,tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTithiTag = itemView.findViewById(R.id.tv_tithi_tag);
            tvDate = itemView.findViewById(R.id.tv_tithi);
        }

        @Override
        public void setData(int position) {

            Description description = list.get(position);
            tvTithiTag.setText(description.getDesc());
            tvDate.setText(description.getDesc1());
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}