package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.AlertModel;

public class Tags_adapter extends AppBaseRecycleAdapter {

    Context context;
    List<AlertModel> list;

    public Tags_adapter(Context context, List<AlertModel> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new Tags_adapter.ViewHolder(inflateLayout(R.layout.item_tags));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvAlertTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAlertTime = itemView.findViewById(R.id.tv_tag);
        }

        @Override
        public void setData(int position) {

            AlertModel alertModel = list.get(position);
            tvAlertTime.setText(alertModel.getAlertTime());
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}