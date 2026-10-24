package com.example.managerdesarcini;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button createButton;
    Button listButton;
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

        listButton = findViewById(R.id.button2);
        listButton.setOnClickListener(e -> {
            Intent intentView = new Intent(MainActivity.this, MainActivity_View.class);
            startActivity(intentView);
        });

        nrSarcini = findViewById(R.id.textView2);
        nrSarcini.setText(String.format("La moment ai %d sarcini", TaskStore.listTODO.size()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        nrSarcini.setText(String.format("La moment ai %d sarcini", TaskStore.listTODO.size()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int selectedItem = item.getItemId();

        if(selectedItem == R.id.create_new_task){
            Intent intentCreate = new Intent(MainActivity.this, MainActivity_Create.class);
            startActivity(intentCreate);
        }

        if(selectedItem == R.id.display_task){
            Intent intentView = new Intent(MainActivity.this, MainActivity_View.class);
            startActivity(intentView);
        }

        if(selectedItem == R.id.remove_task){
            if(TaskStore.listTODO.size() == 0){
                Toast.makeText(this, "Nu sunt sarcini de sters!", Toast.LENGTH_SHORT).show();
            } else {
                TaskStore.listTODO = new HashMap<>();
                TaskStore.listActivated = new HashMap<>();
                nrSarcini.setText(String.format("La moment ai %d sarcini", TaskStore.listTODO.size()));

                Toast.makeText(this, "Toate sarcinile au fost sterse!", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}