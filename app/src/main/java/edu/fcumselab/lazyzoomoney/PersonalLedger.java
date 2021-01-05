package edu.fcumselab.lazyzoomoney;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;


public class PersonalLedger extends AppCompatActivity
{
    PersonalLogTable pltable;
    SQLiteDatabase db;
    TextView txv_username;
    TextView txv_list;
    TextView txv_input;
    TextView txv_output;
    TextView txv_total;

    String username_login = null;
    public int output = 0;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_ledger);

        txv_username = (TextView) findViewById(R.id.txv_username);
        txv_list = (TextView) findViewById(R.id.txv_list);
        txv_input = (TextView) findViewById(R.id.txv_input);
        txv_output = (TextView) findViewById(R.id.txv_output);
        txv_total = (TextView) findViewById(R.id.txv_total);


        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("account")!= null)
        {
            username_login = bundle.getString("account");
            txv_username.setText(username_login);
        }



        ArrayList<String> record_list = new ArrayList<String>();

        pltable = new PersonalLogTable(this);
        db = pltable.db;

        txv_output.setText(Integer.toString(CountMoney("支出")));
        txv_input.setText(Integer.toString(CountMoney("收入")));

        output = CountMoney("支出");

        showLog();



    }

    public void plus(View v)
    {
        Intent it = new Intent(this, AddData.class);
        it.putExtra("account", username_login);
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

    public void showLog() {
        Cursor c = db.rawQuery("SELECT * FROM " + PersonalLogTable.TB_NAME, null);
        if (c.moveToFirst()) {
            String str = "";
            //String str = "總共有" + c.getCount() + "筆資料\n";
            //str += "-----\n";

            do {
                if(!c.getString(0).equals(username_login))
                    continue;
                //str += "User: " + c.getString(0) + "\n";
                str += "money: " + c.getString(1) + "\n";
                str += "item: " + c.getString(2) + "\n";
                str += "wallet: " + c.getString(3) + "\n";
                str += "ledger: " + c.getString(4) + "\n";
                str += "category: " + c.getString(5) + "\n";

            } while (c.moveToNext());
            txv_list.setText(str);

        }

    }

    public int CountMoney(String type)
    {
        int total = 0;
        Cursor c = db.rawQuery("SELECT * FROM " + PersonalLogTable.TB_NAME, null);
        if (c.moveToFirst()) {

            String str = "";
            do {
                if(c.getString(0).equals(username_login) && c.getString(4).equals(type))
                   total += Integer.parseInt(c.getString(1));

            } while (c.moveToNext());

            txv_list.setText(str);
        }


        return total;

    }

    /*public void SearchWallet()
    {
        Cursor c = wallet_db.rawQuery("SELECT * FROM " + WalletTable.TB_NAME, null);
        int money = 0;
        if (c.moveToFirst()) {


            do {
                money = Integer.parseInt(c.getString(0));

            } while (c.moveToNext());

            txv_total.setText(Integer.toString(money));
        }

    }*/

}
