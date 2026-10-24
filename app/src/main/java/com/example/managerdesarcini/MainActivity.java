package com.example.managerdesarcini;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, String> listTODO = new HashMap<>();

    Button createButton;
    TextView nrSarcini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createButton = findViewById(R.id.button);
        createButton.setOnClickListener(e -> {
            Intent intentCreate = new Intent(MainActivity.this, MainActivity_Create.class);
            startActivity(intentCreate);
        });

        nrSarcini = findViewById(R.id.textView2);
        nrSarcini.setText(String.format("La moment ai %d sarcini", listTODO.size()));
    }
}