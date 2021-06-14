package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.base.BaseRecycleAdapter;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.Users;

public class Users_adapter extends AppBaseRecycleAdapter {

    Context context;
    List<Users> list;

    public Users_adapter(Context context, List<Users> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseRecycleAdapter.BaseViewHolder getViewHolder() {
        return new Users_adapter.ViewHolder(inflateLayout(R.layout.item_user_list));
    }

    @Override
    public int getDataCount() {
        // return 10;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseRecycleAdapter.BaseViewHolder {
        TextView tvUserName,tvUserNumber,tvApprove;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvUserNumber = itemView.findViewById(R.id.tv_user_number);
            tvApprove = itemView.findViewById(R.id.tv_approve);
        }

        @Override
        public void setData(int position) {

            Users users = list.get(position);
            tvUserName.setText(users.getName());
            tvUserNumber.setText(users.getMobile());
            //tvAlertTime.setText(alertModel.getAlertTime());
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}