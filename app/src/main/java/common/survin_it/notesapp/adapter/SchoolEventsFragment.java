package common.survin_it.notesapp.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import common.survin_it.notesapp.Dummy.Dummy;
import common.survin_it.notesapp.R;

public class SchoolEventsFragment extends Fragment implements View.OnClickListener{
    Button btn_sports, btn_all,btn_events;
    TextView tv_sports, tv_all,tv_events,tv_music;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_school_events, container, false);
        tv_sports=view.findViewById(R.id.tv_sports);
        tv_all=view.findViewById(R.id.tv_all);
        tv_events=view.findViewById(R.id.tv_events);
        tv_sports.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_events.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_all:
                if(Dummy.btn_all==1){
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_sports = 1;
                    tv_sports.setBackgroundResource(R.drawable.button_selector);
                    tv_sports.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_event = 1;
                    tv_events.setBackgroundResource(R.drawable.button_selector);
                    tv_events.setTextColor(getResources().getColor(R.color.white));
                    break;
                }
                else {
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                    //
                    Dummy.btn_sports = 0;
                    tv_sports.setBackgroundResource(R.drawable.school_button_white);
                    tv_sports.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_event = 0;
                    tv_events.setBackgroundResource(R.drawable.school_button_white);
                    tv_events.setTextColor(getResources().getColor(R.color.black));
                    break;
                }

            case R.id.tv_sports:
                if(Dummy.btn_sports==1){
                    Dummy.btn_sports = 0;
                    tv_sports.setBackgroundResource(R.drawable.school_button_white);
                    tv_sports.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));

                }
                else {
                    Dummy.btn_sports = 1;
                    tv_sports.setBackgroundResource(R.drawable.button_selector);
                    tv_sports.setTextColor(getResources().getColor(R.color.white));

                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            case R.id.tv_events:
                if(Dummy.btn_event==1){
                    Dummy.btn_event = 0;
                    tv_events.setBackgroundResource(R.drawable.school_button_white);
                    tv_events.setTextColor(getResources().getColor(R.color.black));
                    //
                    Dummy.btn_all = 1;
                    tv_all.setBackgroundResource(R.drawable.button_selector);
                    tv_all.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    Dummy.btn_event = 1;
                    tv_events.setBackgroundResource(R.drawable.button_selector);
                    tv_events.setTextColor(getResources().getColor(R.color.white));
                    Dummy.btn_all = 0;
                    tv_all.setBackgroundResource(R.drawable.school_button_white);
                    tv_all.setTextColor(getResources().getColor(R.color.black));
                    break;
                }
        }

    }
}
