package common.survin_it.notesapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import common.survin_it.notesapp.all_spaces.My_SchoolActivity;

public class Adapter_School_events extends FragmentPagerAdapter {
    My_SchoolActivity context;
    int totalTabs;
    public Adapter_School_events(My_SchoolActivity c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SchoolEventsFragment footballFragment = new SchoolEventsFragment();
                return footballFragment;
            case 1:
                SchoolNotesFragment cricketFragment = new SchoolNotesFragment();
                return cricketFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
