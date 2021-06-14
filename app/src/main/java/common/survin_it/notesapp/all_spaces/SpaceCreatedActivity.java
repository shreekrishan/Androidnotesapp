package common.survin_it.notesapp.all_spaces;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chivorn.datetimeoptionspicker.DateTimePickerView;
import com.chivorn.datetimeoptionspicker.OptionsPickerView;
import com.chivorn.datetimeoptionspicker.listener.CustomListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.adapter.Alert_adapter;
import common.survin_it.notesapp.adapter.Tags_adapter;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.AlertModel;
import common.survin_it.notesapp.model.MySpace;
import common.survin_it.notesapp.model.Repeat;

public class SpaceCreatedActivity extends AppCompatActivity {

    TextView startSpaceDate, startTime,endSpaceDate,endTime,tvRepeat;
    private OptionsPickerView dtpvOptions,dtpvNoLinkOptions;
    private ArrayList<Repeat> options1Items = new ArrayList<>();
    private ArrayList<String> alert1 = new ArrayList<>();
    private ArrayList<String> alert2 = new ArrayList<>();
    private ArrayList<String> alert3 = new ArrayList<>();

    private DateTimePickerView  dtpvCustomTime, dtpvCustomLunar,dtpvCustomEndTime,dtpvCustomEndDate;

    private FrameLayout mFrameLayout;
    LinearLayout linearTwo,linearThree;

    //alert items
    RecyclerView rvAlert,rvTags;
    private Alert_adapter alert_adapter;
    private Tags_adapter tags_adapter;
    List<AlertModel> alertList = new ArrayList<>();
    List<AlertModel> tagsList = new ArrayList<>();
    DatePicker datePicker, datePicker1;
    TimePicker timePicker, timePicker1;


    EditText etTag;
    ImageView iv_tick;

    FloatingActionButton btnAdd,btnMenu,btnSearch;
    ImageView ivPosition;

    //details
    LinearLayout linearFour,linearDetail;
    FrameLayout frameEdit;
    LinearLayout frame_cal,frameRepeat,frame_time, frame_cal1,frame_time1;
    EditText etDetail;
    ImageView iv_add_detail;
    TextView tvDetail;

    //image save
    ImageView ivSaveButton;
    TextView tvTitle;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_space);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        startSpaceDate = findViewById(R.id.tv_start_date);
        startTime = findViewById(R.id.tv_start_time);
        endSpaceDate = findViewById(R.id.tv_end_date);
        endTime = findViewById(R.id.tv_end_time);
        mFrameLayout = findViewById(R.id.fragmen_fragment);
        frameRepeat = findViewById(R.id.frame_4);
        tvRepeat = findViewById(R.id.tv_repeat);
        linearTwo = findViewById(R.id.linear_2);
        linearThree = findViewById(R.id.linear_3);
        rvAlert = findViewById(R.id.rv_alert);
        etTag = findViewById(R.id.et_tags);
        iv_tick = findViewById(R.id.iv_add_tag);
        frame_cal = findViewById(R.id.frame_cal);
        datePicker = findViewById(R.id.datePicker);
        datePicker1 = findViewById(R.id.datePicker1);
        timePicker = findViewById(R.id.timePicker);
        timePicker1 = findViewById(R.id.timePicker1);
        frame_time = findViewById(R.id.frame_time);
        frame_cal1 = findViewById(R.id.frame_cal1);
        frame_time1 = findViewById(R.id.frame_time1);

        btnAdd = findViewById(R.id.add_button);
        btnMenu = findViewById(R.id.btn_menu);
        btnSearch = findViewById(R.id.btn_search);
        ivPosition = findViewById(R.id.iv_icon);
        btnAdd.setVisibility(View.GONE);
        ivPosition.setVisibility(View.GONE);

        linearFour = findViewById(R.id.linear_4);
        linearDetail = findViewById(R.id.ll_detail);
        frameEdit = findViewById(R.id.fl_edit);
        tvDetail = findViewById(R.id.tv_detail);
        etDetail = findViewById(R.id.et_detail);
        iv_add_detail = findViewById(R.id.iv_add_detail);
        ivSaveButton = findViewById(R.id.iv_done);
        tvTitle = findViewById(R.id.tv_title);

        linearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameEdit.setVisibility(View.VISIBLE);
            }
        });

        iv_add_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etDetail.getText().toString();
                linearFour.setVisibility(View.GONE);
                frameEdit.setVisibility(View.GONE);
                linearDetail.setVisibility(View.VISIBLE);
                tvDetail.setText(text);
            }
        });

        iv_tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertModel alertModel = new AlertModel();
                alertModel.setAlertTime(etTag.getText().toString());
                etTag.setText("");
                ArrayList<AlertModel> tagData = new ArrayList<>();
                tagData.add(alertModel);
                addTagData(tagData);
            }
        });

        rvAlert = findViewById(R.id.rv_alert);
        rvTags = findViewById(R.id.rv_tags);
        initializeRecyclerView();

        initLunarPicker();
        initCustomTimePicker();
        initLunarEndPicker();
        initOptionPicker();
        getOptionData();
        initCustomTimeEndPicker();
        initNoLinkOptionsPicker();

        startSpaceDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                updatelabel();
                if (frame_cal.getVisibility()==View.VISIBLE)
                {
                    frame_time.setVisibility(View.GONE);
                    frame_cal.setVisibility(View.GONE);
                    //updatelabel();
                }
                else
                {
                    frame_time.setVisibility(View.GONE);
                    frame_cal.setVisibility(View.VISIBLE);
                    updatelabel();

                   /* startSpaceDate.setText( new StringBuilder()
                            // Month is 0 based so add 1
                            .append(day).append("-")
                            .append(month).append("-")
                            .append(year).append(""));*/
                    //datepick();

                }
                //dtpvCustomLunar.show();
            }
        });

        endSpaceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dtpvCustomEndDate.show();
                updatelabel1();
                if (frame_cal1.getVisibility()==View.VISIBLE)
                {
                    frame_time1.setVisibility(View.GONE);
                    frame_cal1.setVisibility(View.GONE);
                    //updatelabel();
                }
                else
                {
                    frame_time1.setVisibility(View.GONE);
                    frame_cal1.setVisibility(View.VISIBLE);
                    updatelabel1();

                   /* startSpaceDate.setText( new StringBuilder()
                            // Month is 0 based so add 1
                            .append(day).append("-")
                            .append(month).append("-")
                            .append(year).append(""));*/
                    //datepick();

                }

            }
        });

        frameRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtpvOptions.show();
            }
        });



        startTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                updatelabeltime();
                if (frame_time.getVisibility()==View.VISIBLE)
                {
                    frame_cal.setVisibility(View.GONE);
                    frame_time.setVisibility(View.GONE);
                }
                else
                {
                    frame_cal.setVisibility(View.GONE);
                    frame_time.setVisibility(View.VISIBLE);
                    updatelabeltime();
                }
               // dtpvCustomTime.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                //dtpvCustomEndTime.show();
                updatelabeltime1();
                if (frame_time1.getVisibility()==View.VISIBLE)
                {
                    frame_cal1.setVisibility(View.GONE);
                    frame_time1.setVisibility(View.GONE);
                }
                else
                {
                    frame_cal1.setVisibility(View.GONE);
                    frame_time1.setVisibility(View.VISIBLE);
                    updatelabeltime1();
                }
            }
        });

        linearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtpvNoLinkOptions.show();
            }
        });

        linearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTag.setVisibility(View.VISIBLE);
                iv_tick.setVisibility(View.VISIBLE);
            }
        });

        ivSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title,startDate,endDate,start_time,end_time,detail;
                title = tvTitle.getText().toString();
                startDate = startSpaceDate.getText().toString();
                start_time = startTime.getText().toString();
                endDate = endSpaceDate.getText().toString();
                end_time = endTime.getText().toString();
                detail = tvDetail.getText().toString();

                if (title.equals("")){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.title_msg,Toast.LENGTH_SHORT).show();
                }
                else if(startDate.equals(R.string.start_date)){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.start_date_msg,Toast.LENGTH_SHORT).show();
                }
                else if(endDate.equals(R.string.end_date)){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.end_date_msg,Toast.LENGTH_SHORT).show();
                }
                else if(start_time.equals(R.string.start_time)){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.start_time_msg,Toast.LENGTH_SHORT).show();
                }
                else if (end_time.equals(R.string.end_time)){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.end_time_msg,Toast.LENGTH_SHORT).show();
                }
                else if (detail.equals(R.string.search_text)){
                    Toast.makeText(SpaceCreatedActivity.this,R.string.detail_msg,Toast.LENGTH_SHORT).show();
                }
                else {
                    MySpace mySpace = new MySpace();
                    mySpace.setTitle(tvTitle.getText().toString());
                    mySpace.setAll_day(false);
                    mySpace.setStart_date(startSpaceDate.getText().toString());
                    mySpace.setStart_time(startTime.getText().toString());
                    mySpace.setEnd_date(endSpaceDate.getText().toString());
                    mySpace.setEnd_time(endTime.getText().toString());
                    mySpace.setRepeat(tvRepeat.getText().toString());
                    mySpace.setDetails(tvDetail.getText().toString());
                    mySpace.setAlerts(alertList);
                    mySpace.setTags(tagsList);

                    Common.db.collection("users")
                            .document(firebaseAuth.getUid())
                            .collection("new_space")
                            .document()
                            .set(mySpace)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    finish();
                                    Toast.makeText(SpaceCreatedActivity.this,"success",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SpaceCreatedActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updatelabeltime1() {
        int minutes=timePicker1.getMinute();
        int hour=timePicker1.getHour();
        if (hour >=13 && hour < 24) {
            hour -= 12;
        }
        else {
            hour = 12;
        }
        String am_pm = (hour < 12) ? "AM" : "PM";
        endTime.setText(hour+":"+minutes+" "+am_pm);
    }

    private void updatelabel1() {
        int day = datePicker1.getDayOfMonth();
        int month = datePicker1.getMonth();
        int year = datePicker1.getYear()-1900;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date d = new Date(year, month, day);
        String strDate = df.format(d);
        endSpaceDate.setText(strDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updatelabeltime() {
        //timePicker.setIs24HourView(false);
        int minutes=timePicker.getMinute();
        int hour=timePicker.getHour();
        if (hour >=13 && hour < 24) {
            hour -= 12;
        }
        else {
            hour = 12;
        }
        String am_pm = (hour < 12) ? "AM" : "PM";
        startTime.setText(hour+":"+minutes+" "+am_pm);
    }

    private void updatelabel() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear()-1900;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date d = new Date(year, month, day);
        String strDate = df.format(d);
        startSpaceDate.setText(strDate);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void datepick() {
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startSpaceDate.setText(view.getDayOfMonth());
            }
        });
    }

    private void initializeRecyclerView() {
        alert_adapter = new Alert_adapter(this, alertList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvAlert.setLayoutManager(linearLayoutManager);
        rvAlert.setAdapter(alert_adapter);
        rvAlert.setNestedScrollingEnabled(true);

        tags_adapter = new Tags_adapter(this, tagsList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvTags.setLayoutManager(linearLayoutManager2);
        rvTags.setAdapter(tags_adapter);
        rvTags.setNestedScrollingEnabled(true);

    }

    private void initOptionPicker() {

        dtpvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getPickerViewText();
                tvRepeat.setText(tx);
            }
        })
                .setTitleText("")
                .setContentTextSize(20)
                .setDividerColor(Color.GRAY)
                .setSelectOptions(0, 1)
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.GRAY)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .isCenterLabel(false)
                .setBackgroundId(0x66000000)
                .build();
        dtpvOptions.setPicker(options1Items);
    }

    private void getOptionData() {

        getNoLinkData();

        //选项1
        options1Items.add(new Repeat("No Repeat"));
        options1Items.add(new Repeat("Every Day"));
        options1Items.add(new Repeat("Every Week"));
        options1Items.add(new Repeat("Every Month"));
        options1Items.add(new Repeat("Every Year"));
        options1Items.add(new Repeat("Custom"));

    }

    private void getNoLinkData() {
        alert1.add("Before");

        alert2.add("Minutes");
        alert2.add("Hours");
        alert2.add("Days");
        alert2.add("Week");

        alert3.add("00");
        alert3.add("05");
        alert3.add("10");
        alert3.add("15");
        alert3.add("20");
        alert3.add("25");
        alert3.add("30");
        alert3.add("35");
        alert3.add("40");
        alert3.add("45");
        alert3.add("50");
        alert3.add("55");
        alert3.add("60");
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        dtpvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                AlertModel alertModel = new AlertModel();
                alertModel.setAlertTime(alert1.get(options1)+" "+alert2.get(options2)+" "+alert3.get(options3));

                ArrayList<AlertModel> alertData = new ArrayList<>();
                alertData.add(alertModel);
                addData(alertData);
                String str = "1:" + alert1.get(options1)
                        + "\n2:" + alert2.get(options2)
                        + "\n3:" + alert3.get(options3);

            }
        }).build();
        dtpvNoLinkOptions.setNPicker(alert1, alert2, alert3);
    }

    private void addData(List<AlertModel> levelList) {
      //  this.alertList.clear();
        if (levelList != null) {
            this.alertList.addAll(levelList);
        }
        if (alert_adapter != null) {
            alert_adapter.notifyDataSetChanged();
            // updateNoDataView();
        }
    }
    private void addTagData(List<AlertModel> levelList) {
      //  this.alertList.clear();
        if (levelList != null) {
            this.tagsList.addAll(levelList);
        }
        if (tags_adapter != null) {
            tags_adapter.notifyDataSetChanged();
            // updateNoDataView();
        }
    }

    private void initLunarPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 12, 31);
        //时间选择器 ，自定义布局
        dtpvCustomLunar = new DateTimePickerView.Builder(this, new DateTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
               // Toast.makeText(MySpaceActivity.this, getDate(date), Toast.LENGTH_SHORT).show();
                startSpaceDate.setText(getDate(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.datetimeoptionspicker_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomLunar.returnData();
                                dtpvCustomLunar.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomLunar.dismiss();
                            }
                        });
//                        //公农历切换
//                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
//                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                dtpvCustomLunar.setLunarCalendar(!dtpvCustomLunar.isLunarCalendar());
//                                //自适应宽
//                                setTimePickerChildWeight(v, 0.8f, isChecked ? 1f : 1.1f);
//                            }
//                        });

                    }
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timepicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timepicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timepicker.getChildCount(); i++) {
                            View childAt = timepicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private void initCustomTimePicker() {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        dtpvCustomTime = new DateTimePickerView.Builder(this, new DateTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                startTime.setText(getTime(date));
            }
        })
                /*.setType(DateTimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.gravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.datetimeoptionspicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomTime.returnData();
                                dtpvCustomTime.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentSize(18)
                .setType(new boolean[]{false, false, false, true, true, true})
                .setLabel("y", "m", "d", "h", "min", "s")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private void initLunarEndPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 12, 31);
        //时间选择器 ，自定义布局
        dtpvCustomEndDate = new DateTimePickerView.Builder(this, new DateTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
               // Toast.makeText(MySpaceActivity.this, getDate(date), Toast.LENGTH_SHORT).show();
                endSpaceDate.setText(getDate(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.datetimeoptionspicker_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomEndDate.returnData();
                                dtpvCustomEndDate.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomEndDate.dismiss();
                            }
                        });
//                        //公农历切换
//                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
//                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                                dtpvCustomLunar.setLunarCalendar(!dtpvCustomLunar.isLunarCalendar());
//                                //自适应宽
//                                setTimePickerChildWeight(v, 0.8f, isChecked ? 1f : 1.1f);
//                            }
//                        });

                    }
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timepicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timepicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timepicker.getChildCount(); i++) {
                            View childAt = timepicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private void initCustomTimeEndPicker() {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        dtpvCustomEndTime = new DateTimePickerView.Builder(this, new DateTimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                endTime.setText(getTime(date));
            }
        })
                /*.setType(DateTimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.gravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.datetimeoptionspicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomEndTime.returnData();
                                dtpvCustomEndTime.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dtpvCustomEndTime.dismiss();
                            }
                        });
                    }
                })
                .setContentSize(18)
                .setType(new boolean[]{false, false, false, true, true, true})
                .setLabel("y", "m", "d", "h", "min", "s")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
    private String getDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMMM-dd");
        return format.format(date);
    }
}