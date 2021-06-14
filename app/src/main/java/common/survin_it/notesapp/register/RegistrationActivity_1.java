package common.survin_it.notesapp.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.common.Common;

public class RegistrationActivity_1 extends AppCompatActivity {

    EditText tv_number,tv_name;
    Button btn_next;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_1);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tv_number = findViewById(R.id.enter_number);
        tv_name = findViewById(R.id.enter_name);
        btn_next = findViewById(R.id.btn_next);
        iv_back = findViewById(R.id.iv_back);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv_number.getText().toString().equals("")){
                    Toast.makeText(RegistrationActivity_1.this,"enter number.", Toast.LENGTH_SHORT).show();
                }
                else if(tv_name.getText().toString().equals("")){
                    Toast.makeText(RegistrationActivity_1.this,"enter name.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //store user data locally
                    SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MySharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("user_number", tv_number.getText().toString());
                    myEdit.putString("user_name", tv_name.getText().toString());
                    myEdit.commit();
                    Common.number = tv_number.getText().toString();
                    Common.name = tv_name.getText().toString();

                    Query query = Common.db.collection("users")
                            .whereEqualTo("mobile",tv_number.getText().toString());
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            boolean documentExists;

                            if (task.isSuccessful()) {
                                documentExists = !task.getResult().isEmpty();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Common.userUniqueID = document.getId();
                                    //Toast.makeText(RegistrationActivity_1.this,document.getId(),Toast.LENGTH_SHORT).show();
                                   // Log.d(TAG, document.getId() + " => " + document.getData());
                                }

                            }else {
                                documentExists = false;
                            }
                            if(documentExists) {
                                Intent intent = new Intent(RegistrationActivity_1.this, SelectLocationActivity.class);
                                startActivity(intent);
                                //Toast.makeText(RegistrationActivity_1.this,"Success",Toast.LENGTH_SHORT).show();

                            }else{
                                Common.userUniqueID = "0";
                                Intent intent = new Intent(RegistrationActivity_1.this, SelectLocationActivity.class);
                                startActivity(intent);
                                //Toast.makeText(RegistrationActivity_1.this,"fail",Toast.LENGTH_SHORT).show();
                            }
//
                        }
                    });


                }

//                String mobile = tv_number.getText().toString().trim();
//
//                if(mobile.isEmpty() || mobile.length() < 10){
//                    tv_number.setError("Enter a valid mobile");
//                    tv_number.requestFocus();
//                    return;
//                }
//
//                Intent intent = new Intent(RegistrationActivity_1.this, OtpActivity.class);
//                intent.putExtra("mobile", mobile);
//                startActivity(intent);
            }
        });
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            Intent intent = new Intent(this, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//
//    }
}