package common.survin_it.notesapp.home;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.MySpacesActivity;
import common.survin_it.notesapp.all_spaces.My_SchoolActivity;
import common.survin_it.notesapp.all_spaces.Myspace_detailsActivity;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.settings.SettingsActivity;
import common.survin_it.notesapp.settings.Settings_CalendarActivity;
import common.survin_it.notesapp.spaces.SpacesActivity;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);
        tabLayout=v.findViewById(R.id.tabLayout);
        viewPager=v.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Notes"));
        tabLayout.addTab(tabLayout.newTab().setText("Spaces"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        Intent i=new Intent(getContext(), Settings_CalendarActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent ii=new Intent(getContext(), My_SchoolActivity.class);
                        startActivity(ii);
                        break;
                    case 2:
                        Intent k=new Intent(getContext(), SpacesActivity.class);
                        startActivity(k);
                        break;
                        case 3:
                        Intent kk=new Intent(getContext(), SettingsActivity.class);
                        startActivity(kk);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
     /*   Button algo_button = v.findViewById(R.id.algo_button);
        Button course_button = v.findViewById(R.id.course_button);

        algo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "Algorithm Shared", Toast.LENGTH_SHORT)
                        .show();
                dismiss();
            }
        });

        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                        "Course Shared", Toast.LENGTH_SHORT)
                        .show();
                dismiss();
            }
        });*/
        return v;
    }
}

