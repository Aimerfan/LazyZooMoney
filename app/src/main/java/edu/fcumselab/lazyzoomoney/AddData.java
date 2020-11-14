package edu.fcumselab.lazyzoomoney;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static edu.fcumselab.lazyzoomoney.Register.db_name;

public class AddData extends AppCompatActivity {

    SQLiteDatabase db;
    TextView money, item, wallet, ledger, category;
    Toast tos;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        money = (EditText) findViewById(R.id.money);
        item = (EditText) findViewById(R.id.item);
        wallet = (EditText) findViewById(R.id.wallet);
        ledger = (EditText) findViewById(R.id.ledger);
        category = (EditText) findViewById(R.id.category);

        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null); // 開啟資料表
        String createTable = "CREATE TABLE IF NOT EXISTS " + "Personal_Log" + "(Money INTEGER, " + "Item VARCHAR(10),"  + "Wallet VARCHAR(10)," + "Ledger VARCHAR(5)," + "Category VARCHAR(10) )";
        db.execSQL(createTable);
    }

    private void addData(int money, String item, String wallet, String ledger, String category)
    {
        ContentValues cv = new ContentValues(3);
        cv.put("money", money);
        cv.put("item", item);
        cv.put("wallet", wallet);
        cv.put("ledger", ledger);
        cv.put("category", category);

        db.insert("Personal_Log", null, cv);

    }

    public void addbutton(View v)
    {
        if(money.getText().toString().isEmpty())
        {
            tos.setText("請輸入金額");
            tos.show();
        }
        else if(item.getText().toString().isEmpty())
        {
            tos.setText("請輸入項目");
            tos.show();
        }
        else if(wallet.getText().toString().isEmpty())
        {
            tos.setText("請輸入錢包");
            tos.show();
        }
        else if(ledger.getText().toString().isEmpty())
        {
            tos.setText("請輸入收支");
            tos.show();
        }
        else if(category.getText().toString().isEmpty())
        {
            tos.setText("請輸入類別");
            tos.show();
        }
        else
        {
            addData(Integer.parseInt(money.getText().toString()), item.getText().toString(), wallet.getText().toString(), ledger.getText().toString(), category.getText().toString());
            tos.setText("新增成功");
            tos.show();
            Intent it = new Intent(this, PersonalLedger.class); // 頁面跳轉
            startActivity(it);
        }


    }
}
