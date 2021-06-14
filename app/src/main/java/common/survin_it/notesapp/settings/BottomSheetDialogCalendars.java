package common.survin_it.notesapp.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.Dummy.Dummy;
import common.survin_it.notesapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to.
 * create an instance of this fragment.
 */
public class BottomSheetDialogCalendars extends BottomSheetDialogFragment implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout ll_share,ll_share1,ll_krishan,ll_krishan1;
    TextView tv_remove,tv_remove1;
    TextView tv_sports, tv_all,tv_events,tv_music;
    ImageView iv_cross;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.bottom_sheet_layout_claendars,
                container, false);
        tv_sports=view.findViewById(R.id.tv_sports);
        tv_all=view.findViewById(R.id.tv_all);
        tv_events=view.findViewById(R.id.tv_events);
        iv_cross=view.findViewById(R.id.iv_cross);
        tv_sports.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_events.setOnClickListener(this);
        iv_cross.setOnClickListener(this);
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
            case R.id.iv_cross:
                getDialog().dismiss();
                break;


        } }

}

