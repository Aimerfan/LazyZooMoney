package edu.fcumselab.lazyzoomoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddWallet extends AppCompatActivity
{

    private static final String db_name = "LazyZooMoney.db";
    private static final String tb_name = "Wallet";
    SQLiteDatabase db;

    EditText nameR, moneyR, commentR;
    Spinner spinnerR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addwallet);
        spinnerR = (Spinner) findViewById(R.id.spinner);
        nameR = (EditText) findViewById(R.id.nameR);
        moneyR = (EditText) findViewById(R.id.moneyR);
        commentR = (EditText) findViewById(R.id.commentR);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS " + tb_name
                + "(Kind CHAR(10)," +
                "Name CHAR(30)," +
                "Money INT," +
                "Comment VARCHAR(256));";
        db.execSQL(sql);
    }

    private void clearR() {
        spinnerR.setSelection(1);
        nameR.setText(null);
        moneyR.setText(null);
        commentR.setText(null);
        db.close();
    }

    private void addData(String kind, String name, int money, String comment) {
        ContentValues wallet = new ContentValues();
        wallet.put("Kind", kind);
        wallet.put("Name", name);
        wallet.put("Money", money);
        wallet.put("Comment", comment);
        db.insert(tb_name, null, wallet);
    }

    public void createWallet(View v) {
        String kind = spinnerR.getSelectedItem().toString();
        String name = nameR.getText().toString();
        String money = moneyR.getText().toString();
        String comment = commentR.getText().toString();
        int initMoney;

        if (name.isEmpty() || money.isEmpty()){
            return;
        }
        try {
            initMoney = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            return;
        }

        addData(kind, name, initMoney, comment);

        clearR();
    }

}
