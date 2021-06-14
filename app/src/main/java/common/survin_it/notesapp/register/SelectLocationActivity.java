package common.survin_it.notesapp.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chivorn.datetimeoptionspicker.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import common.survin_it.notesapp.GoodMorning2Activity;
import common.survin_it.notesapp.GoodMorningActivity;
import common.survin_it.notesapp.R;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.model.CardBean;
import common.survin_it.notesapp.model.NewUser;
import common.survin_it.notesapp.model.Users;
import common.survin_it.notesapp.settings.Settings_AccountActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class SelectLocationActivity extends AppCompatActivity {

    TextView et_district,et_town,et_district_tag,et_town_tag;
    private OptionsPickerView dtpvOptions,dtpvTownsOptions;
    List<String> cities = new ArrayList<>();
    List<String> towns = new ArrayList<>();
    String[] pickerVals;
    Button btnEnter;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    SharedPreferences sharedPreferences;

    // firebase integrate
    private FirebaseFirestore db;

    String uuid;

    NumberPicker np_district,np_town;
    String[] data,dataTown;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        np_district = findViewById(R.id.np_district);
        np_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                np_district.setVisibility(View.GONE);
                et_district_tag.setVisibility(View.VISIBLE);
            }
        });
        np_town = findViewById(R.id.np_town);
        np_town.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                np_town.setVisibility(View.GONE);
                et_town_tag.setVisibility(View.VISIBLE);
            }
        });
        //np_district.setOnValueChangedListener(new SelectLocationActivity());

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getOptionData();
        initOptionPicker();
        initTownsOptionPicker();
//





        et_district = findViewById(R.id.et_district) ;
        et_town = findViewById(R.id.et_town);
        et_district_tag = findViewById(R.id.et_district_tag) ;
        et_town_tag = findViewById(R.id.et_town_tag);


        btnEnter = findViewById(R.id.btn_enter);

        et_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dtpvOptions.show();
                np_district.setVisibility(View.VISIBLE);
                db.collection("districts")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {

                            @Override
                            public void onEvent(QuerySnapshot snapshots,
                                                FirebaseFirestoreException e) {
                                if (e != null) {
                                    System.err.println("Listen failed:" + e);
                                    return;
                                }

                                for (DocumentSnapshot doc : snapshots) {
                                    if (doc.get("name") != null) {
                                        // String[] pickerVals = new String[]
                                        cities.add(doc.getString("name"));

                                        // ref2.setValue(strNameList);
                                    }
                                }
                                data = new String[cities.size()];
                                data = cities.toArray(data);

                                np_district.setMinValue(0);
                                np_district.setMaxValue(data.length - 1);
                                np_district.setDisplayedValues(data);
                                //np_district.setValue(0);
                                np_district.setWrapSelectorWheel(false);
                                et_district_tag.setText(String.valueOf(data[0]));

                                //et_district.setText(String.valueOf(np_district.getValue()));

                                np_district.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                    @Override
                                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                        et_district_tag.setText(String.valueOf(data[newVal]));
                                    }
                                });

                            }
                        });
            }
        });

        et_town.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                np_town.setVisibility(View.VISIBLE);
                db.collection("towns")
                        .whereEqualTo("district",et_district_tag.getText().toString())
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {

                            @Override
                            public void onEvent(QuerySnapshot snapshots,
                                                FirebaseFirestoreException e) {
                                if (e != null) {
                                    System.err.println("Listen failed:" + e);
                                    return;
                                }

                                for (DocumentSnapshot doc : snapshots) {
                                    if (doc.get("name") != null) {
                                        // String[] pickerVals = new String[]
                                        towns.add(doc.getString("name"));

                                        // ref2.setValue(strNameList);
                                    }
                                }
                                dataTown = new String[towns.size()];
                                dataTown = towns.toArray(dataTown);

                                np_town.setMinValue(0);
                                np_town.setMaxValue(dataTown.length - 1 );
                                np_town.setDisplayedValues(dataTown);
                                //np_district.setValue(0);
                                np_town.setWrapSelectorWheel(false);
                                et_town_tag.setText(String.valueOf(dataTown[0]));

                                //et_district.setText(String.valueOf(np_district.getValue()));

                                np_town.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                    @Override
                                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                        et_town_tag.setText(String.valueOf(dataTown[newVal]));
                                    }
                                });

                            }
                        });
            }
        });

//        et_town.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dtpvTownsOptions.show();
//            }
//        });

        //et_town.setDisplayedValues(towns);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (Common.isChangeLocation.equals("0")){
                if (et_district_tag.getText().toString().equals("District")) {
                    Toast.makeText(SelectLocationActivity.this, "select district.", Toast.LENGTH_SHORT).show();
                } else if (et_town_tag.getText().toString().equals("Town")) {
                    Toast.makeText(SelectLocationActivity.this, "select town.", Toast.LENGTH_SHORT).show();
                } else {
                    //store user data locally
                    sharedPreferences = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("user_district", et_district_tag.getText().toString().trim());
                    myEdit.putString("user_town", et_town_tag.getText().toString().trim());
                    myEdit.commit();

                    Common.district = et_district_tag.getText().toString();
                    Common.town = et_town_tag.getText().toString();

                    NewUser newUser = new NewUser();
                    newUser.setNumber(Common.number);
                    newUser.setName(Common.name);
                    newUser.setDistrict(Common.district);
                    newUser.setTown(Common.town);
                    newUser.setStatus("0");

                    uuid = UUID.randomUUID().toString();

                    db.collection("new_users").document(uuid)
                            .set(newUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    sharedPreferences = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.putString("userUUID", uuid);
                                    myEdit.commit();

                                    Intent intent = new Intent(SelectLocationActivity.this, GoodMorningActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(SelectLocationActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
                 else {
                     if (et_district_tag.getText().toString().equals("District")) {
                         Toast.makeText(SelectLocationActivity.this, "select district.", Toast.LENGTH_SHORT).show();
                     } else if (et_town_tag.getText().toString().equals("Town")) {
                         Toast.makeText(SelectLocationActivity.this, "select town.", Toast.LENGTH_SHORT).show();
                     } else {
                         //store user data locally
                         sharedPreferences = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                         SharedPreferences.Editor myEdit = sharedPreferences.edit();
                         myEdit.putString("user_district", et_district_tag.getText().toString().trim());
                         myEdit.putString("user_town", et_town_tag.getText().toString().trim());
                         myEdit.commit();

                         Common.district = et_district_tag.getText().toString();
                         Common.town = et_town_tag.getText().toString();

                         db.collection("users").document(firebaseAuth.getUid())
                                 .update("district_id",et_district_tag.getText().toString(),
                                         "town_id",et_town_tag.getText().toString())
                                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {
                                         Toast.makeText(SelectLocationActivity.this, "success", Toast.LENGTH_SHORT).show();

                                         Intent intent = new Intent(SelectLocationActivity.this, Settings_AccountActivity.class);
                                         startActivity(intent);
                                         finish();

                                     }
                                 })
                                 .addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(Exception e) {
                                         Toast.makeText(SelectLocationActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                     }
                                 });
                     }
                 }

            }
        });


    }

    private void getOptionData() {

    //    getCardData();
        //getNoLinkData();

//        options1Items.add(new District(0, "Banglore"));
//        options1Items.add(new District(1, "Mysore"));
//        options1Items.add(new District(2, "Koyattam"));

//        ArrayList<String> options2Items_01 = new ArrayList<>();
//        options2Items_01.add("BTown01");
//        options2Items_01.add("BTown02");
//        options2Items_01.add("BTown03");
//        options2Items_01.add("BTown04");
//        ArrayList<String> options2Items_02 = new ArrayList<>();
//        options2Items_02.add("MTown01");
//        options2Items_02.add("MTown02");
//        options2Items_02.add("MTown03");
//        options2Items_02.add("MTown04");
//        ArrayList<String> options2Items_03 = new ArrayList<>();
//        options2Items_03.add("KTown01");
//        options2Items_03.add("KTown01");
//        options2Items_03.add("KTown01");
//        options2Items_03.add("KTown01");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);

    }

    private void initOptionPicker() {

        dtpvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = cities.get(options1);
                et_district.setText(tx);
                towns.clear();
                db.collection("towns")
                        .whereEqualTo("district_name",cities.get(options1))
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {

                            @Override
                            public void onEvent(QuerySnapshot snapshots,
                                                FirebaseFirestoreException e) {
                                if (e != null) {
                                    System.err.println("Listen failed:" + e);
                                    return;
                                }

                                for (DocumentSnapshot doc : snapshots) {
                                    if (doc.get("name") != null) {
                                        towns.add(doc.getString("name"));
                                    }
                                }
                            }
                        });
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
        dtpvOptions.setPicker(cities);
    }

    private void initTownsOptionPicker() {

        dtpvTownsOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = towns.get(options1);
                //et_town.setText(tx);
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
        dtpvTownsOptions.setPicker(towns);
    }
    int currentItemNum = 1;
    private void getCardData() {
        int itemCount = currentItemNum + 5;
        for (int i = currentItemNum; i < itemCount; i++) {
            cardItem.add(new CardBean(i, "No.ABC " + i));
            currentItemNum++;
        }

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 9) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 9) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }
    }

//    @Override
//    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//        district = String.valueOf(newVal);
//    }

}