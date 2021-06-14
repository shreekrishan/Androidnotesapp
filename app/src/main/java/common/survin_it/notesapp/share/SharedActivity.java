package common.survin_it.notesapp.share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.AdapterSharedSpaces;
import common.survin_it.notesapp.adapter.Adapter_School_events;
import common.survin_it.notesapp.home.BottomSheetDialogShare;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    FloatingActionButton fb_menu;
    LinearLayout linearSpace;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        fb_menu = findViewById(R.id.btn_menu);
        fb_menu.setOnClickListener(this);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calendar_event).setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mobile).setText("Notes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final AdapterSharedSpaces adapter = new AdapterSharedSpaces(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        tabLayout.getTabCount();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_menu:
                BottomSheetDialogShare bottomSheet = new BottomSheetDialogShare();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
                break;
        }
    }
}