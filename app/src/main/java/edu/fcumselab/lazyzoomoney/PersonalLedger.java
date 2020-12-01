package edu.fcumselab.lazyzoomoney;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;


public class PersonalLedger extends AppCompatActivity
{
    PersonalLogTable pltable;
    SQLiteDatabase db;
    TextView test_txv;
    ListView record;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_ledger);

        test_txv = (TextView) findViewById(R.id.test_txv);
        ListView record = this.findViewById(R.id.record);
        ArrayList<String> record_list = new ArrayList<String>();

        pltable = new PersonalLogTable(this);
        db = pltable.db;

        Cursor c = db.rawQuery("SELECT * FROM " + "Personal_Log",null);

        if(c.moveToFirst() && c.getCount() != 0)
        {
            String str = "總共有" + c.getCount() + "筆資料\n";
            str += "-----\n";

            while(c.moveToFirst() && c.getCount() != 0)
            {
                str += "money: " + c.getString(0) + " ";
                str += "item: " + c.getString(1) + " ";
                str += "wallet: " + c.getString(2) + " ";
                str += "ledger: " + c.getString(3) + " ";
                str += "category: " + c.getString(4) + " ";
                record_list.add(str);
            }
            test_txv.setText(str);
            ArrayAdapter<String> addrecord = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, record_list);
            record.setAdapter(addrecord);
        }
    }

    public void plus(View v)
    {
        Intent it = new Intent(this, AddData.class);
        startActivity(it);
    }

    public void btn_wallet(View v)
    {
        Intent it = new Intent(this, Wallet.class);
        startActivity(it);
    }

}
