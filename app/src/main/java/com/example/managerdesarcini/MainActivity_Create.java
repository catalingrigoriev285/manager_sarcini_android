package com.example.managerdesarcini;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity_Create extends AppCompatActivity {
    Button backButton;
    Button addTaskButton;
    EditText inputTask;

    CheckBox chxPersonal;
    CheckBox chxProfesional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.button6);
        backButton.setOnClickListener(e -> {
            finish();
        });

        inputTask = findViewById(R.id.editTextText);

        chxPersonal = findViewById(R.id.checkBox);
        chxProfesional = findViewById(R.id.checkBox2);

        chxPersonal.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(isChecked){
                if(chxProfesional.isChecked()){
                    chxProfesional.setChecked(false);
                }
            }
        }));

        chxProfesional.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(isChecked){
                if(chxPersonal.isChecked()){
                    chxPersonal.setChecked(false);
                }
            }
        }));

        addTaskButton = findViewById(R.id.button5);
        addTaskButton.setOnClickListener(e -> addTaskItem(inputTask.getText().toString()));
    }

    public void addTaskItem(String task){
        if(task.isBlank()){
            Toast.makeText(this, "Nu poti pune un task gol!", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<Integer, String> tasks = TaskStore.listTODO;
        tasks.put(tasks.size() + 1, task);

        Toast.makeText(this, "Adaugat cu success!", Toast.LENGTH_SHORT).show();
        inputTask.setText("");
    }
}