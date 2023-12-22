package com.example.demosqlite.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demosqlite.database.DbHelper;
import com.example.demosqlite.model.ToDo;

import java.util.ArrayList;

public class ToDoDao {
    private final DbHelper dbHelper;

    public ToDoDao(Context context) {
        dbHelper= new DbHelper(context);
    }
    public ArrayList<ToDo> getListToDo()
    {
        //tao 1 dsach de add dữ liệu
        ArrayList<ToDo> list= new ArrayList<>();
        SQLiteDatabase database  =  dbHelper.getReadableDatabase();
        //db chạy
        database.beginTransaction();
        try{
            Cursor cursor= database.rawQuery("SELECT * FROM TODO",null);
            if (cursor.getCount()>0)
            {
                cursor.moveToFirst();
                do{
                    list.add(new ToDo(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e )
        {
            Log.e(TAG,"get ListToDo: "+e);
        }
        finally {
            database.endTransaction();
        }
        return list;
    }
    public  long addToDo(ToDo toDo)
    {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.beginTransaction();
        //Su dung contentValues ddeeer đưa dữ liệu vào db
        ContentValues values= new ContentValues();
        values.put("TITLE",toDo.getTitle());
        values.put("CONTENT",toDo.getContent());
        values.put("DATE",toDo.getDate());
        values.put("TYPE",toDo.getType());
        values.put("STATUS",toDo.getStatus());
        //if thanh cong trả về giá trị tương ứng
        long check=db.insert("TODO",null,values);
        return check;
    }
    public  boolean updateToDo(ToDo toDo)
    {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.beginTransaction();
        //Su dung contentValues ddeeer đưa dữ liệu vào db
        ContentValues values= new ContentValues();
        values.put("TITLE",toDo.getTitle());
        values.put("CONTENT",toDo.getContent());
        values.put("DATE",toDo.getDate());
        values.put("TYPE",toDo.getType());
        values.put("STATUS",toDo.getStatus());
        //if thanh cong trả về giá trị tương ứng
        long check=db.update("TODO",values,"id=?",
                new String[]{String.valueOf(toDo.getId()
                )});
        return check!=-1;

    }
    public  boolean DelteteToDo(int id)
    {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.beginTransaction();
        long check=db.delete("TODO","id=?",
                new String[]{String.valueOf(id)});
        return check!=-1;

    }
    public long getLastInsertedId() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long lastId = -1;

        Cursor cursor = db.rawQuery("SELECT last_insert_rowid(); ", null);
        if (cursor != null && cursor.moveToFirst()) {
            lastId = cursor.getLong(0);
            cursor.close();
        }

        db.close();
        return lastId;
    }


}
