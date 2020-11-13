package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name,age,email,phone;
    private Button button;

    public final static String
            MESSAGE_KEY ="com.example.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameId);
        age = findViewById(R.id.ageId);
        email = findViewById(R.id.emailId);
        phone = findViewById(R.id.phoneId);

        button = findViewById(R.id.buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              senData();

            }
        });


    }
    public void senData(){
        String nameTex = name.getText().toString();
        String ageTex = age.getText().toString();
        String emailTex = email.getText().toString();
        String phoneTex = phone.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("nameTex", nameTex);
        bundle.putString("ageTex", ageTex);
        bundle.putString("emailTex", emailTex);
        bundle.putString("phoneTex", phoneTex);

        Intent intent = new Intent( this,ShowProfile.class);
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(this, "Data send perfectly...", Toast.LENGTH_SHORT).show();
    }
}
