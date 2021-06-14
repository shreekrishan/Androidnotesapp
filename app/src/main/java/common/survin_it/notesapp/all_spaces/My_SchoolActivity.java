package common.survin_it.notesapp.all_spaces;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Adapter_School_events;
import common.survin_it.notesapp.home.BottomSheetDialogShare;
import common.survin_it.notesapp.home.EventDetailsActivity;
import common.survin_it.notesapp.home.EventListByCalenderActivity;

public class My_SchoolActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    FloatingActionButton fb_menu,add_button;
    LinearLayout linearSpace;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView iv_cross1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myschool_space);

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
        iv_cross1=findViewById(R.id.iv_cross1);
        fb_menu = findViewById(R.id.btn_menu);
        add_button = findViewById(R.id.add_button);
        fb_menu.setOnClickListener(this);
        add_button.setOnClickListener(this);
        iv_cross1.setOnClickListener(this);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calendar).setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.panchang_icon).setText("Notes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final Adapter_School_events adapter = new Adapter_School_events(this,getSupportFragmentManager(),
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

            case R.id.add_button:
                Intent intent = new Intent(My_SchoolActivity.this, Myspace_detailsActivity.class);
                startActivity(intent);
                break;
                case R.id.btn_menu:
                BottomSheetDialogShare bottomSheet = new BottomSheetDialogShare();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
                break;
                case R.id.iv_cross1:
              Intent i=new Intent(this,SelectSpaceActivity.class);
              startActivity(i);
                break;
        }
    }
}
