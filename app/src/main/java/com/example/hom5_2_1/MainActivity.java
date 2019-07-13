package com.example.hom5_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN_FILE_NAME = "login_text";
    private static final String PASS_FILE_NAME = "pass_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loginTextView = findViewById(R.id.login);
        final EditText passwordTextView = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.btn_login);
        Button registrationButton = findViewById(R.id.btn_registration);

        registrationButton.setOnClickListener(new View.OnClickListener() {  //сохраняем файл
            @Override
            public void onClick(View v) {
                String loginStr = loginTextView.getText().toString();
                String passwordStr = passwordTextView.getText().toString();

                if (loginStr.equals("") || passwordStr.equals("")) {
                    Toast.makeText(MainActivity.this, (R.string.nou_pass_nou_login), Toast.LENGTH_LONG).show();
                } else {

                    saveData(LOGIN_FILE_NAME, loginStr);
                    saveData(PASS_FILE_NAME, passwordStr);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {   //читаем файл
            @Override
            public void onClick(View v) { //--------------------------------------------------------------

                readLineFromFile(LOGIN_FILE_NAME);
                readLineFromFile(PASS_FILE_NAME);
            }
        });
    }

    private String readLineFromFile(String fileName) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            final InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(streamReader);
            Toast.makeText(MainActivity.this,(R.string.read_text) , Toast.LENGTH_LONG).show();

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

    private void saveData(String fileName, String text) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(MainActivity.this, (R.string.save_text), Toast.LENGTH_LONG).show();
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
