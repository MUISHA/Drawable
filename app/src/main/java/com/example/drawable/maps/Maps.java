package com.example.drawable.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drawable.R;

public class Maps extends AppCompatActivity {
    EditText editText1,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        editText1 = findViewById(R.id.et_source);
        editText2 = findViewById(R.id.et_destination);
        button = findViewById(R.id.btn_location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eSource = editText1.getText().toString().trim();
                String eDestination = editText2.getText().toString().trim();
                if (eSource.equals("") && eDestination.equals(""))
                {
                    Toast.makeText(Maps.this, "Entre location", Toast.LENGTH_SHORT).show();
                }else
                {
                    DisplayTraking(eSource, eDestination);
                }
            }
        });
    }

    private void DisplayTraking(String eSource, String eDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + eSource
                    + "/" + eDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException exp)
        {
           Uri uri = Uri.parse("https://play.google.com/store/apps/" +
                   "details?id=com.google.android.apps.maps");
           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(intent);
        }
    }
}