package com.example.managerdesarcini;

import android.os.Bundle;
import android.widget.Button;
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