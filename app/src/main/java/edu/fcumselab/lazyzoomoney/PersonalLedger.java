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
import edu.fcumselab.lazyzoomoney.dbhelper.WalletTable;


public class PersonalLedger extends AppCompatActivity
{
    PersonalLogTable pltable;
    SQLiteDatabase db;
    TextView test_txv;
    ListView record;
    String username_login = null;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_ledger);

        test_txv = (TextView) findViewById(R.id.test_txv);


        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("account")!= null)
        {
            username_login = bundle.getString("account");
            test_txv.setText(username_login);
        }



        ListView record = this.findViewById(R.id.record);
        ArrayList<String> record_list = new ArrayList<String>();

        pltable = new PersonalLogTable(this);
        db = pltable.db;
        showLog();
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

    public void btn_group(View v)
    {
        Intent it = new Intent(this, Group.class);
        it.putExtra("account", username_login);
        startActivity(it);

    }

    private void showLog() {
        Cursor c = db.rawQuery("SELECT * FROM " + PersonalLogTable.TB_NAME, null);
        if (c.moveToFirst()) {
            String str = "總共有" + c.getCount() + "筆資料\n";
            str += "-----\n";

            do {
                str += "money: " + c.getString(0) + "\n";
                str += "item: " + c.getString(1) + "\n";
                str += "wallet: " + c.getString(2) + "\n";
                str += "ledger: " + c.getString(3) + "\n";
                str += "category: " + c.getString(4) + "\n";
            } while (c.moveToNext());

            test_txv.setText(str);
        }
    }

}
