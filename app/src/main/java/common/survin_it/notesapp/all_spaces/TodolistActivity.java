package common.survin_it.notesapp.all_spaces;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Alert_adapter;
import common.survin_it.notesapp.adapter.Tags_adapter;
import common.survin_it.notesapp.adapter.Todo_adapter;
import common.survin_it.notesapp.home.BottomSheetDialogShare;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.TodoModel;

public class TodolistActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnAdd, btn_menu, btnSearch;
    TextView tv_todolink;
    RecyclerView rv_marketing;
    private Todo_adapter todo_adapter;
    List<TodoModel> todoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        tv_todolink=findViewById(R.id.tv_todolink);
        btn_menu=findViewById(R.id.btn_menu);
        btnAdd=findViewById(R.id.add_button);
        rv_marketing=findViewById(R.id.rv_marketing);

        btnAdd.setVisibility(View.GONE);
        btn_menu.setOnClickListener(this);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        todo_adapter = new Todo_adapter(this, todoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_marketing.setLayoutManager(linearLayoutManager);
        rv_marketing.setAdapter(todo_adapter);
        rv_marketing.setNestedScrollingEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btn_menu:
             Intent i=new Intent(this,MylinkActivity.class);
             startActivity(i);
                break;

        }

    }
}
