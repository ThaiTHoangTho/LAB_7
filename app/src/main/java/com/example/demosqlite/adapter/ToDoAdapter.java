package com.example.demosqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demosqlite.R;
import com.example.demosqlite.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private Context context;
    private List<ToDo> tasks;

    public ToDoAdapter( Context context, List<ToDo> tasks) {
        super(context, 0,tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Nullable
    @Override
    public ToDo getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDo task= getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_todo,parent,false);
        }
        //anh xạ
        TextView tvTile= convertView.findViewById(R.id.tvTitle);
        TextView tvContent= convertView.findViewById(R.id.tvContent);
        TextView tvDate= convertView.findViewById(R.id.tvdate);
        TextView tvtype= convertView.findViewById(R.id.tvtype);
        //lấy data
        tvTile.setText(task.getTitle());
        tvContent.setText(task.getContent());
        tvDate.setText(task.getDate());
        tvtype.setText(task.getType());
        return convertView;
    }

}
