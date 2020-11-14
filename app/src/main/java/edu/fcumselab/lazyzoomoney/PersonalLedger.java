package edu.fcumselab.lazyzoomoney;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static edu.fcumselab.lazyzoomoney.Register.db_name;

public class PersonalLedger extends AppCompatActivity {

    SQLiteDatabase db;
    TextView test_txv;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_ledger);

        test_txv = (TextView) findViewById(R.id.tset_txv);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM " + "Personal_Log",null);
        if(c.moveToFirst() && c.getCount() != 0)
        {
            String str = "總共有" + c.getCount() + "筆資料\n";
            str += "-----\n";

            do{
                str += "money: " + c.getString(0) + "\n";
                str += "item: " + c.getString(1) + "\n";
                str += "wallet: " + c.getString(2) + "\n";
                str += "ledger: " + c.getString(3) + "\n";
                str += "category: " + c.getString(4) + "\n";
            }while(c.moveToNext());
            test_txv.setText(str);


        }
    }

    public void plus(View v)
    {
        Intent it = new Intent(this, AddData.class);
        startActivity(it);
    }

}
