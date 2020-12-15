package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;

import edu.fcumselab.lazyzoomoney.dbhelper.AccountTable;
import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;

public class Group extends AppCompatActivity {

    String username_login = null;
    SQLiteDatabase db;
    TextView txv;
    GroupTable grouptable;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        txv = (TextView) findViewById(R.id.txv);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("account") != null)
            username_login = bundle.getString("account");

        grouptable = new GroupTable(this);
        db = grouptable.db;
        txv.setText(username_login);
        showLog();

    }

    public void btn_addgroup(View v)
    {
        Intent it = new Intent(this, AddGroup.class);
        it.putExtra("account", username_login);
        startActivity(it);

    }

    private void showLog() {

        Cursor c = db.rawQuery("SELECT * FROM " + GroupTable.TB_NAME, null);
        //c.moveToFirst();
        if (c.moveToFirst()) {
            String str = "總共有" + c.getCount() + "筆資料\n";
            str += "-----\n";

            do {
                str += "GroupLedgerID: " + c.getString(0) + "\n";
                str += "UserID: " + c.getString(1) + "\n";
            } while (c.moveToNext());

            txv.setText(str);
        }
    }


}
