package com.example.hom5_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN_FILE_NAME = "login text";
    private static final String PASS_FILE_NAME = "pass text";
    private Editable loginEditable;
    private Editable passEditable;
    private EditText loginTextView;
    private EditText passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTextView = findViewById(R.id.login);
        passwordTextView = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.btn_login);
        Button registrationButton = findViewById(R.id.btn_registration);
        loginEditable = loginTextView.getText();
        passEditable = passwordTextView.getText();

        registrationButton.setOnClickListener(new View.OnClickListener() {  // onclick  сохраняем файл
            @Override
            public void onClick(View v) {

                String loginStr = loginTextView.getText().toString();
                String passwordStr = passwordTextView.getText().toString();

                if (TextUtils.isEmpty(loginStr) || TextUtils.isEmpty(passwordStr)) {
                    Toast.makeText(MainActivity.this, (R.string.nou_pass_nou_login), Toast.LENGTH_LONG).show();
                } else {

                    saveData(LOGIN_FILE_NAME, loginStr);  ///сохраняем файл
                    saveData(PASS_FILE_NAME, passwordStr);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {   // onclick читаем файл
            @Override
            public void onClick(View v) { //--------------------------------------------------------------

                String loginReturn = null;
                String passReturn = null;
                String loginStr = loginTextView.getText().toString();
                String passwordStr = passwordTextView.getText().toString();

                if (TextUtils.isEmpty(loginStr) || TextUtils.isEmpty(passwordStr)) {
                    Toast.makeText(MainActivity.this, (R.string.nou_pass_nou_login), Toast.LENGTH_LONG).show();
                } else {
                    loginReturn = readLineFromFile(LOGIN_FILE_NAME);   //читаем файл
                    passReturn = readLineFromFile(PASS_FILE_NAME);
                }
                if (loginEditable.toString().equals(loginReturn) && passEditable.toString().equals(passReturn)) {
                    Toast.makeText(MainActivity.this, (R.string.access), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MainActivity.this, (R.string.no_access), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private String readLineFromFile(String fileName) {  // ф-я чтения

        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            final InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(streamReader);

            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(String fileName, String text) {   // ф-я записи

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}