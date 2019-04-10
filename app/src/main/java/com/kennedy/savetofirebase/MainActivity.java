package com.kennedy.savetofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.inputNames)
    EditText inputNames;

    @BindView(R.id.inputAge)
    EditText inputAge;

    @BindView(R.id.inputGender)
    EditText inputGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       /* String sentence="The Quick Brown Fox Jumped Over A Lazy Dog";
        HashMap<String,String> data=new HashMap<>();
        data.put("sentence",sentence);
        data.put("country","Kenya");
        data.put("age","Female");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("myData");

        myRef.setValue(data);*/

    }
    @OnClick(R.id.btnSave)
    public  void save(){
        String names= inputNames.getText().toString().trim();
        String age= inputAge.getText().toString().trim();
        String gender= inputGender.getText().toString().trim();



        //check validity

        if (names.isEmpty()|| age.isEmpty()|| gender.isEmpty()){
            return;
        }

          String sentence="The Quick Brown Fox Jumped Over A Lazy Dog";
        HashMap<String,String> data=new HashMap<>();
        data.put("names",names);
        data.put("age",age);
        data.put("gender",gender);

        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("students");

        myRef.push().setValue(data).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                inputNames.setText("");
                inputAge.setText("");
                inputGender.setText("");

                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
