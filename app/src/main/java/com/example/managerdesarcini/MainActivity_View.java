package com.example.managerdesarcini;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity_View extends AppCompatActivity {

    Button buttonBack;
    LinearLayout listLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonBack = findViewById(R.id.button4);
        buttonBack.setOnClickListener(e -> this.finish());

        listLayout = findViewById(R.id.listLayout);
        displayTasks("all");
    }

    @Override
    protected void onResume(){
        super.onResume();
        displayTasks("all");
    }

    @SuppressLint("SetTextI18n")
    public void displayTasks(String type){
        listLayout.removeAllViews();

        if(Objects.equals(type, "personal") && Collections.frequency(TaskStore.listType.values(), "personal") == 0){
            TextView temp = new TextView(this);
            temp.setTextColor(Color.BLACK);
            temp.setText("Nu ai sarcini disponibile!");
            listLayout.addView(temp);

            return;
        }

        if(Objects.equals(type, "professional") && Collections.frequency(TaskStore.listType.values(), "professional") == 0){
            TextView temp = new TextView(this);
            temp.setTextColor(Color.BLACK);
            temp.setText("Nu ai sarcini disponibile!");
            listLayout.addView(temp);

            return;
        }

        if(TaskStore.listTODO.isEmpty()){
            TextView temp = new TextView(this);
            temp.setTextColor(Color.BLACK);
            temp.setText("Nu ai sarcini disponibile!");
            listLayout.addView(temp);
        } else {
            for(int i = 1; i <= TaskStore.listTODO.size(); i++){
                final int taskId = i;

                CheckBox chx = new CheckBox(this);
                chx.setTextColor(Color.BLACK);
                chx.setText(String.format("[%s] %s", TaskStore.listType.get(i), TaskStore.listTODO.get(i)));

                chx.setOnCheckedChangeListener(((buttonView, isChecked) -> {
                    if(isChecked){
                        TaskStore.listActivated.put(taskId, true);
                    }
                }));

                if(Boolean.TRUE.equals(TaskStore.listActivated.get(i))){
                    chx.setChecked(true);
                }

                if(Objects.equals(type, "personal")){
                    if(Objects.equals(TaskStore.listType.get(taskId), "personal")){
                        listLayout.addView(chx);
                        return;
                    }
                } else if(Objects.equals(type, "professional")){
                    if(Objects.equals(TaskStore.listType.get(taskId), "professional")){
                        listLayout.addView(chx);
                        return;
                    }
                } else {
                    listLayout.addView(chx);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedItem = item.getItemId();

        if (selectedItem == R.id.menu_view_all) {
            displayTasks("all");
        } else if (selectedItem == R.id.menu_view_personal) {
            displayTasks("personal");
        } else if (selectedItem == R.id.menu_view_profesional) {
            displayTasks("professional");
        } else if (selectedItem == R.id.menu_view_remove_all){
            TaskStore.listTODO = new HashMap<>();
            TaskStore.listActivated = new HashMap<>();
            TaskStore.listType = new HashMap<>();

            Toast.makeText(this, "Toate sarcinile au fost sterse!", Toast.LENGTH_SHORT).show();
            displayTasks("all");
        }

        return super.onOptionsItemSelected(item);
    }
}