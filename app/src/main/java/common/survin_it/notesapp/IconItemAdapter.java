package common.survin_it.notesapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.adapter.EventItemAdapter;
import common.survin_it.notesapp.model.Description;
import common.survin_it.notesapp.model.Icon;

public class IconItemAdapter extends AppBaseRecycleAdapter {

    Context context;
    List<Icon> list;

    public IconItemAdapter(Context context, List<Icon> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new IconItemAdapter.ViewHolder(inflateLayout(R.layout.icon_item));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }

        @Override
        public void setData(int position) {

            Icon icon = list.get(position);
            Picasso.get().load(icon.getIcon()).into(ivIcon);
            //tvTithiTag.setText(description.getDesc());
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}