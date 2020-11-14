package edu.fcumselab.lazyzoomoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddWallet extends AppCompatActivity
{

    static final String db_name = "User";
    static final String tb_name = "Wallet";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addwallet);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name + "(account VARCHAR(16), " + "name VARCHAR(16),"  + "money INT(16))";
        db.execSQL(createTable);
    }
}
