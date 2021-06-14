package common.survin_it.notesapp.all_spaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chivorn.datetimeoptionspicker.OptionsPickerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Adapter_School_events;
import common.survin_it.notesapp.home.BottomSheetDialogShare;

public class TextColorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView startSpaceDate, startTime,endSpaceDate,endTime,tvRepeat;
    private OptionsPickerView dtpvOptions,dtpvNoLinkOptions;
    private FrameLayout mFrameLayout;
    LinearLayout linearTwo,linearThree;



    FloatingActionButton add_button,btnMenu,btnSearch;
    ImageView iv_cross1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_color);

        add_button=findViewById(R.id.add_button);
        btnMenu=findViewById(R.id.btn_menu);
        iv_cross1=findViewById(R.id.iv_cross1);
        iv_cross1.setOnClickListener(this);
        add_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_button:
                Intent intent = new Intent(TextColorActivity.this, MySpaceDateTimeActivity.class);
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
