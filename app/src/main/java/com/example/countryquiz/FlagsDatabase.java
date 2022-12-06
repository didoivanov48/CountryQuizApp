package com.example.countryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FlagsDatabase extends SQLiteOpenHelper {

    public FlagsDatabase(@Nullable Context context) {
        super(context, "countryquiz.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"countryquiz\" (\n" +
                "\t\"flag_id\"\tINTEGER,\n" +
                "\t\"flag_name\"\tTEXT,\n" +
                "\t\"flag_image\"\tTEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS countryquiz");
        onCreate(sqLiteDatabase);
    }
}
