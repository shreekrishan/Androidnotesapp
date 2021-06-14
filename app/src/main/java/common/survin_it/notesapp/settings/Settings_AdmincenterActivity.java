package common.survin_it.notesapp.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.all_spaces.SelectSpaceActivity;
import common.survin_it.notesapp.register.SelectLocationActivity;

public class Settings_AdmincenterActivity extends AppCompatActivity implements View.OnClickListener {
ImageView iv_cross, iv_icon;
LinearLayout ll_localevents,ll_localeventsdetail;
FloatingActionButton btn_menu, add_button;
TextView tv_approvalcenter,tv_subscription,tv_deleteaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admincenter);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        iv_cross=findViewById(R.id.iv_cross);
        btn_menu=findViewById(R.id.btn_menu);
        tv_approvalcenter=findViewById(R.id.tv_approvalcenter);
        tv_subscription=findViewById(R.id.tv_subscription);
        add_button=findViewById(R.id.add_button);
        iv_icon=findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        tv_approvalcenter.setOnClickListener(this);
        tv_subscription.setOnClickListener(this);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_AdmincenterActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;
                case R.id.tv_approvalcenter:
                Intent intet = new Intent(Settings_AdmincenterActivity.this, Settings_AdminApprovalcenterActivity.class);
                startActivity(intet);
                break;
case R.id.tv_subscription:
                Intent inet = new Intent(Settings_AdmincenterActivity.this, Settings_AdminSubscriptionrActivity.class);
                startActivity(inet);
                break;

        } }

}