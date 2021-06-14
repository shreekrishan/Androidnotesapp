package common.survin_it.notesapp.all_spaces;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import common.survin_it.notesapp.R;

public class MylinkActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnAdd, btnMenu, btnSearch;
    TextView tv_todolink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylink);
        tv_todolink=findViewById(R.id.tv_todolink);
        btnAdd=findViewById(R.id.add_button);

        btnAdd.setVisibility(View.GONE);

    }
    @Override
    public void onClick(View v) {

    }
}
