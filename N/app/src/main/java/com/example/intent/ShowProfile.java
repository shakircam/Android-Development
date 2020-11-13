package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowProfile extends AppCompatActivity {
    private TextView name,email,age,phone;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        name = findViewById(R.id.nameId);
        age = findViewById(R.id.ageId);
        email = findViewById(R.id.emailId);
        phone = findViewById(R.id.phoneId);
        button = findViewById(R.id.button2Id);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String nameSh = bundle.getString("nameTex");
            String ageSh = bundle.getString("ageTex");
            String emailSh = bundle.getString("emailTex");
            String phoneSh = bundle.getString("phoneTex");

            name.setText("  Name : " + nameSh);
            age.setText("  Age : " + ageSh);
            email.setText("  Email : " + emailSh);
            phone.setText("  Phone : " + phoneSh);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProfile.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
