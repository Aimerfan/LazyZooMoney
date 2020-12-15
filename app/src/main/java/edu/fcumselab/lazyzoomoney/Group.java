package edu.fcumselab.lazyzoomoney;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;

import edu.fcumselab.lazyzoomoney.dbhelper.AccountTable;
import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;

public class Group extends AppCompatActivity {

    String username_login = null;
    GroupTable group;
    SQLiteDatabase db;
    TextView txv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("account") != null)
            username_login = bundle.getString("account");

        group = new GroupTable(this);
        db = group.db;
        showLog();

    }

    private void showLog() {
        Cursor c = db.rawQuery("SELECT * FROM " + PersonalLogTable.TB_NAME, null);
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
