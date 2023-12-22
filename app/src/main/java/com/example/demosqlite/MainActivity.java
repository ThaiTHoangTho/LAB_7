package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.demosqlite.adapter.ToDoAdapter;
import com.example.demosqlite.dao.ToDoDao;
import com.example.demosqlite.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView todoListView;
    ToDoAdapter toDoAdapter;
    ToDoDao toDoDao;
    private EditText titleEditText;
    private EditText contentEditText;
    private EditText Eddate;
    private EditText Edtype;
    private int selectedItemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoListView = findViewById(R.id.lvToDo);
        toDoDao = new ToDoDao(this);
        ArrayList<ToDo> list = toDoDao.getListToDo();
        toDoAdapter = new ToDoAdapter(this, list);
        todoListView.setAdapter(toDoAdapter);
        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnupdate = findViewById(R.id.buttonUpdate);
        Button btndel = findViewById(R.id.buttonDel);
        titleEditText = findViewById(R.id.txtTitle);
        contentEditText = findViewById(R.id.txtContent);
        Eddate = findViewById(R.id.txtDate);
        Edtype = findViewById(R.id.txtType);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //lay data
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String date = Eddate.getText().toString();
                String type = Edtype.getText().toString();
                ToDo a = new ToDo();
                a.setTitle(title);
                a.setContent(content);
                a.setDate(date);
                a.setType(type);
                a.setStatus(0);
                boolean success ;
                long check=toDoDao.addToDo(a);
                    refreshToDoList();
                    clearEditTexts();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String date = Eddate.getText().toString();
                String type = Edtype.getText().toString();
                ToDo a = new ToDo();
                a.setTitle(title);
                a.setContent(content);
                a.setDate(date);
                a.setType(type);
                boolean check ;
                if (selectedItemId != -1) {
                    // Update existing ToDo
                    a.setId(selectedItemId);
                    check = toDoDao.updateToDo(a);
                    if (check) {
                        refreshToDoList();
                        clearEditTexts();
                    }
                }

            }
        });
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDo selectedData = toDoAdapter.getItem(position);

                // Update EditTexts with selected item's title and content
                titleEditText.setText(selectedData.getTitle());
                contentEditText.setText(selectedData.getContent());
                Eddate.setText(selectedData.getDate());
                Edtype.setText(selectedData.getType());
                selectedItemId = selectedData.getId();

            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemId != -1) {
                    boolean success = toDoDao.DelteteToDo(selectedItemId);
                    if (success) {
                        refreshToDoList();
                        clearEditTexts();
                    }
                }
            }
        });
    }
    private void refreshToDoList() {
        toDoAdapter.clear();
        toDoAdapter.addAll(toDoDao.getListToDo());
        toDoAdapter.notifyDataSetChanged();
    }
    private void clearEditTexts() {
        titleEditText.setText("");
        contentEditText.setText("");
        Eddate.setText("");
        Edtype.setText("");
        selectedItemId = -1;
    }

}