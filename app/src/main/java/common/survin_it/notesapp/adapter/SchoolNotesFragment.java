package common.survin_it.notesapp.adapter;

import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import common.survin_it.notesapp.R;


public class SchoolNotesFragment extends Fragment  implements View.OnClickListener{
    TextView tv_todolist;
    LinearLayout ll_todolist,ll_goal,ll_year2020,ll_year2021;
    View view1,view2,view3,view4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_school_notes, container, false);
        tv_todolist=view.findViewById(R.id.tv_todolist);
        ll_todolist=view.findViewById(R.id.ll_todolist);
        ll_goal=view.findViewById(R.id.ll_goal);
        ll_year2020=view.findViewById(R.id.ll_year2020);
        ll_year2021=view.findViewById(R.id.ll_year2021);
        view1=view.findViewById(R.id.view1);
        view2=view.findViewById(R.id.view2);
        view3=view.findViewById(R.id.view3);
        view4=view.findViewById(R.id.view4);
        ll_goal.setOnClickListener(this);
        ll_todolist.setOnClickListener(this);
        ll_year2020.setOnClickListener(this);
        ll_year2021.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_todolist:
                if (ll_goal.getVisibility() == View.VISIBLE) {
                    ll_todolist.setVisibility(View.VISIBLE);
                    ll_year2020.setVisibility(View.GONE);
                    ll_goal.setVisibility(View.GONE);
                    ll_year2021.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);

                }
                else
                {

                    ll_goal.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.ll_goal:
                if (ll_year2020.getVisibility() == View.VISIBLE) {
                    ll_year2020.setVisibility(View.GONE);
                    ll_year2021.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                }
                else{
                    ll_year2020.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.ll_year2020:
                if (ll_year2021.getVisibility() == View.VISIBLE) {
                    ll_year2021.setVisibility(View.GONE);
                    view3.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.GONE);
                }
                else{
                    ll_year2021.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.VISIBLE);
                }
                break;

        }
    }
}
