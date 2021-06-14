package common.survin_it.notesapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import common.survin_it.notesapp.AppBase.AppBaseRecycleAdapter;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.TodoModel;

public class Todo_adapter extends AppBaseRecycleAdapter {

    Context context;
    List<TodoModel> list;

    public Todo_adapter(Context context, List<TodoModel> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public BaseViewHolder getViewHolder() {
        return new Todo_adapter.ViewHolder(inflateLayout(R.layout.marketing_list));
    }

    @Override
    public int getDataCount() {
         //return 5;

        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends BaseViewHolder {
       CheckBox ch_marketing;

        public ViewHolder(View itemView) {
            super(itemView);
            ch_marketing = itemView.findViewById(R.id.ch_marketing);
        }

        @Override
        public void setData(int position) {

            TodoModel todoModel = list.get(position);
            ch_marketing.setText(todoModel.getMarketing());
        }

        @Override
        public void onClick(View v) {
            performItemClick((Integer) v.getTag(), v);
        }

    }
}