package com.example.registrationform.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registrationform.R;
import com.example.registrationform.database.StoreDatabase;

import static com.example.registrationform.database.StoreDatabase.COLUMN_USERNAME;
import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_CITY;
import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_EMAIL;
import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_PASSWORD;
import static com.example.registrationform.database.StoreDatabase.COLUMN_USER_PHONE;
import static com.example.registrationform.database.StoreDatabase.TABLE_USERS;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private StoreDatabase storeDatabase;
    private SQLiteDatabase sqbd;

    EditText et_username;
    EditText et_email;
    EditText et_password;
    EditText et_phone;
    EditText et_city;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Registration Form");

        storeDatabase = new StoreDatabase(this);
        sqbd = storeDatabase.getWritableDatabase();

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        et_city = findViewById(R.id.et_city);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_submit:
                if(TextUtils.isEmpty(et_username.getText())){
                    et_username.setError("Fill again");
                }
                if(TextUtils.isEmpty(et_email.getText())){
                    et_email.setError("Fill again");
                }
                if(TextUtils.isEmpty(et_password.getText())){
                    et_password.setError("Fill again");
                }
                if(TextUtils.isEmpty(et_phone.getText())){
                    et_phone.setError("Fill again");
                }
                if(TextUtils.isEmpty(et_city.getText())){
                    et_city.setError("Fill again");
                }




                    ContentValues versionValues = new ContentValues();
                    versionValues.put(COLUMN_USERNAME,et_username.getText().toString());
                    versionValues.put(COLUMN_USER_EMAIL,et_email.getText().toString());
                    versionValues.put(COLUMN_USER_PASSWORD,et_password.getText().toString());
                    versionValues.put(COLUMN_USER_PHONE,et_phone.getText().toString());
                    versionValues.put(COLUMN_USER_CITY,et_city.getText().toString());

                    sqbd.insert(TABLE_USERS, null, versionValues);
                    Toast.makeText(this, "Inserted to Database", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);

                break;
            case R.id.btn_login:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;
        }


    }
}