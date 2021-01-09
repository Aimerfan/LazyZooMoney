package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;

public class AddData extends AppCompatActivity {

    SQLiteDatabase db;
    TextView money, item;
    Spinner ledger_spinner, wallet_spinner, category_spinner;
    Toast tos;
    PersonalLogTable personal_log;
    String username_login = "";
    String temp_ledger = "";
    String temp_wallet = "";
    String temp_category = "";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("account")!= null)
            username_login = bundle.getString("account");

        money = (EditText) findViewById(R.id.money);
        item = (EditText) findViewById(R.id.item);

        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        personal_log = new PersonalLogTable(this);

        ledger_spinner = (Spinner) findViewById(R.id.ledger_spinner);
        wallet_spinner = (Spinner) findViewById(R.id.wallet_spinner);
        category_spinner = (Spinner) findViewById(R.id.category_spinner);


        ArrayAdapter adapter_ledger = ArrayAdapter.createFromResource(this
                ,R.array.Ledger_arr,android.R.layout.simple_dropdown_item_1line);
        ledger_spinner.setAdapter(adapter_ledger);

        ledger_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                temp_ledger = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter_wallet = ArrayAdapter.createFromResource(this
                ,R.array.walletKind,android.R.layout.simple_dropdown_item_1line);
        wallet_spinner.setAdapter(adapter_wallet);

        wallet_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                temp_wallet = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter adapter_category = ArrayAdapter.createFromResource(this
                ,R.array.Aategory_arr,android.R.layout.simple_dropdown_item_1line);
        category_spinner.setAdapter(adapter_category);

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(),parent.getSelectedItem().toString()/*這行可直接取得選中內容*/,Toast.LENGTH_SHORT).show();
                temp_category = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /*private void addData(int money, String item, String wallet, String ledger, String category)
    {
        ContentValues cv = new ContentValues(3);
        cv.put("money", money);
        cv.put("item", item);
        cv.put("wallet", wallet);
        cv.put("ledger", ledger);
        cv.put("category", category);

        db.insert("Personal_Log", null, cv);

    }*/

    public void addbutton(View v)
    {
        ledger_spinner.setSelection(1);

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
        /*else if(wallet.getText().toString().isEmpty())
        {
            tos.setText("請輸入錢包");
            tos.show();
        }*/
        /*else if(ledger.getText().toString().isEmpty())
        {
            tos.setText("請輸入收支");
            tos.show();
        }*/
        /*else if(category.getText().toString().isEmpty())
        {
            tos.setText("請輸入類別");
            tos.show();
        }*/
        else
        {

            personal_log.insert(username_login, Integer.parseInt(money.getText().toString()), item.getText().toString(), temp_wallet, temp_ledger, temp_category);
            tos.setText("新增成功");
            tos.show();
            Intent it = new Intent(this, PersonalLedger.class); // 頁面跳轉
            it.putExtra("account", username_login);
            startActivity(it);
        }


    }
}
