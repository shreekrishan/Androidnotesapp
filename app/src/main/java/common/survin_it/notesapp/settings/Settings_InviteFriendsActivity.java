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

public class Settings_InviteFriendsActivity extends AppCompatActivity implements View.OnClickListener {
ImageView iv_cross,iv_icon;
LinearLayout ll_localevents,ll_localeventsdetail;
FloatingActionButton btn_menu, btn_search,add_button;
TextView tv_changenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_invite_friends);
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
        btn_search=findViewById(R.id.btn_search);
        add_button=findViewById(R.id.add_button);
        iv_icon=findViewById(R.id.iv_icon);
        iv_cross.setOnClickListener(this);
        btn_search.setVisibility(View.GONE);
        add_button.setVisibility(View.GONE);
        iv_icon.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_cross:
                Intent intent = new Intent(Settings_InviteFriendsActivity.this, SelectSpaceActivity.class);
                startActivity(intent);
                break;

               /* case R.id.btn_menu:
                    BottomSheetDialogCalendars bottomSheet = new BottomSheetDialogCalendars();
                    bottomSheet.show(getSupportFragmentManager(),
                            "ModalBottomSheet");
                break;*/
        } }

}