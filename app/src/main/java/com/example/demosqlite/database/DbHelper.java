package com.example.demosqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    public DbHelper(@Nullable Context context) {
        super(context, "ToDoDatbase", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
///tao câu lệnh tạo sql
        String sql="CREATE TABLE TODO (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "TITLE TEXT, CONTENT TEXT,DATE TEXT,TYPE TEXT, STATUS INTEGER)";
        db.execSQL(sql);
        //tao câu lẹnh thêm dữ liệu
        String data="INSERT INTO TODO VALUES(1,'Học Java','Học Java cơ bản','28/12/2023','Bình thường',1)," +
                "(2,'Học React Native','Học React Native cơ bản','11/11/2023','Khó',0),"+
                "(3,'Học Kotlin','Học Kotlin cơ bản','28/11/2023','Dễ',0)";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <2) {
            // Thực hiện các câu lệnh SQL cập nhật từ phiên bản 1 lên phiên bản 2
            db.execSQL("DROP TABLE IF EXISTS TODO");
            onCreate(db);

        }
    }
}
