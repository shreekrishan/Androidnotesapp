package common.survin_it.notesapp.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import common.survin_it.notesapp.R;
import common.survin_it.notesapp.common.Common;
import common.survin_it.notesapp.home.HomeActivity;
import common.survin_it.notesapp.model.Users;

public class OtpActivity extends AppCompatActivity {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;

    String mobile;


    //firebase auth object
    private FirebaseAuth mAuth;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button otp;

    String name,number,district,town;
    Users user;

    String useeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        user = new Users();

        //initializing objects
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.otp);
        otp = findViewById(R.id.validate_button);


        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        sendVerificationCode(mobile);

        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.validate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });
    }
    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    //the callback to detect the verification status
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences sh = OtpActivity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            String userID = sh.getString("userUUID", "");
                            name = sh.getString("user_name", "");
                            number = sh.getString("user_number", "");
                            district = sh.getString("user_district", "");
                            town = sh.getString("user_town", "");

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                            String currentDateTime = simpleDateFormat.format(new Date());



                            //Users user = new Users();
                            user.setName(name);
                            user.setMobile(number);
                            user.setDistrict_id(district);
                            user.setTown_id(town);
                            user.setState_id("Karnataka");
                            user.setLang_id("0");
                            user.setStatus(1);
                            user.setVerified("1");
                            user.setOtp("0");
                            user.setDeleted("0");
                            user.setCreated_at(currentDateTime);
                            user.setUpdated_at(currentDateTime);
                            firebaseAuth = FirebaseAuth.getInstance();
                            Common.db.collection("users").document(firebaseAuth.getUid())
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
//                                            Common.db.collection("users").document(Common.userUniqueID)
//                                                    .delete();
                                            Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(OtpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                }
                            });
////                                Common.db.collection("users").document(Common.userUniqueID)
////                                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
////                                            @Override
////                                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
////                                                //String status = value.getString("status");
////
////                                                //user.setAddminitstratedBy(value.getString("addminitstratedBy"));
////                                                useeName = value.getString("name");
//////                                                user.setCreated_at(value.getString("created_at"));
//////                                                user.setDeleted(value.getString("deleted"));
//////                                                user.setDistrict_id(value.getString("district_id"));
//////                                                user.setLang_id(value.getString("lang_id"));
//////                                                user.setMobile(value.getString("mobile"));
//////                                                user.setName(value.getString("name"));
//////                                                user.setOtp(value.getString("otp"));
//////                                                user.setState_id(value.getString("state_id"));
//////                                              //  user.setStatus(Integer.parseInt(value.getString("status")));
//////                                                user.setSubscriptionDuration(value.getString("subscriptionDuration"));
//////                                                user.setSubscriptionEndDate(value.getString("subscriptionEndDate"));
//////                                                user.setSubscriptionPlanType(value.getString("subscriptionPlanType"));
//////                                                user.setSubscriptionType(value.getString("subscriptionType"));
//////                                                user.setTown_id(value.getString("town_id"));
//////                                                user.setUpdated_at(value.getString("updated_at"));
//////                                                user.setUserType(value.getString("userType"));
//////                                                user.setVerified(value.getString("verified"));
////                                            }
////                                        });
//                                Users users = new Users();
//                                users.setName(useeName);
//
//                                Common.db.collection("users").document(firebaseAuth.getUid())
//                                        .set(users)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Common.db.collection("users").document(Common.userUniqueID)
//                                                        .delete();
//                                                Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(OtpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
//                                    }
//                                });


                        }
                        else {
                            //verification unsuccessful.. display an error message
                            String message = "Somthing is wrong, we will fix it soon...";

//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                message = "Invalid code entered...";
//                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });


    }
}