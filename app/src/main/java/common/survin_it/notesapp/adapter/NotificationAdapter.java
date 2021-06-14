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
import common.survin_it.notesapp.model.UserNotification;

public class NotificationAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<UserNotification> list;

    public NotificationAdapter(Context context, List<UserNotification> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new NotificationAdapter.ViewHolder(inflateLayout(R.layout.notification_item));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        TextView tvDes;
        ImageView remove;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDes = itemView.findViewById(R.id.tv_content);
            remove = itemView.findViewById(R.id.iv_cross);
        }

        @Override
        public void setData(int position) {

            UserNotification notification = list.get(position);
            tvDes.setText(notification.getDescription());
            remove.setTag(position);
            remove.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}