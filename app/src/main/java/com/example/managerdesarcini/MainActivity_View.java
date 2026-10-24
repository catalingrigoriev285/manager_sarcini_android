package com.example.managerdesarcini;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        listLayout.removeAllViews();

        if(TaskStore.listTODO.size() == 0){
            TextView temp = new TextView(this);
            temp.setText("Nu ai sarcini disponibile!");
            listLayout.addView(temp);
        } else {
            for(int i = 1; i <= TaskStore.listTODO.size(); i++){
                CheckBox chx = new CheckBox(this);
                chx.setText(TaskStore.listTODO.get(i));
                listLayout.addView(chx);
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        listLayout.removeAllViews();

        if(TaskStore.listTODO.size() == 0){
            TextView temp = new TextView(this);
            temp.setText("Nu ai sarcini disponibile!");
            listLayout.addView(temp);
        } else {
            for(int i = 1; i <= TaskStore.listTODO.size(); i++){
                final int taskId = i;

                CheckBox chx = new CheckBox(this);
                chx.setText(TaskStore.listTODO.get(i));

                chx.setOnCheckedChangeListener(((buttonView, isChecked) -> {
                    if(isChecked){
                        TaskStore.listActivated.put(taskId, true);
                    }
                }));

                if(Boolean.TRUE.equals(TaskStore.listActivated.get(i)) == true){
                    chx.setChecked(true);
                }

                listLayout.addView(chx);
            }
        }
    }
}