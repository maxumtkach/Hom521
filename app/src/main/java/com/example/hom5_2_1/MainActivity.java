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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loginTextView = findViewById(R.id.login);
        final EditText passwordTextView = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.btn_login);
        Button registrationButton = findViewById(R.id.btn_registration);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginStr = loginTextView.getText().toString();
                String passwordStr = passwordTextView.getText().toString();

                FileOutputStream fisLog = null;
                FileOutputStream fisPass = null;
                if (loginStr.equals("") && passwordStr.equals("")) {
                    Toast.makeText(MainActivity.this, "Вы ничего не ввели", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        fisLog = openFileOutput("login_text", Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fisLog);
                    BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                    try {
                        bw.write(loginStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        fisPass = openFileOutput("pass_text", Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputStreamWriterPass = new OutputStreamWriter(fisPass);
                    BufferedWriter bwPass = new BufferedWriter(outputStreamWriterPass);
                    try {
                        bwPass.write(passwordStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bwPass.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileInputStream fileInputStreamLog = null;
                FileInputStream fileInputStreamPass = null;
                String strLogin = null;
                String strPassword = null;
                try {
                    fileInputStreamLog = openFileInput("login_text");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                InputStreamReader inputStreamReaderLog = new InputStreamReader(fileInputStreamLog);
                BufferedReader readerLog = new BufferedReader(inputStreamReaderLog);
                try {
                    strLogin = readerLog.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fileInputStreamPass = openFileInput("pass_text");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                InputStreamReader inputStreamReaderPass = new InputStreamReader(fileInputStreamPass);
                BufferedReader readerPass = new BufferedReader(inputStreamReaderPass);
                try {
                    strPassword = readerPass.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Ваш лгин: " + strLogin + '\n' + "Ваш пароль: " + strPassword, Toast.LENGTH_LONG).show();
            }
        });
    }
}
