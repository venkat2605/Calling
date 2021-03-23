package com.example.exp8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    private int request_call =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextPhone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makecall();
            }
        });
    }

    private void makecall() {
        String num= editText.getText().toString();
        if (num.trim().length()>0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, request_call);
            } else {
                String dail = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
            }
        }
            else {
            Toast.makeText(MainActivity.this,"Enter Valid Number",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==request_call){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makecall();
            }
            else {
                Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}