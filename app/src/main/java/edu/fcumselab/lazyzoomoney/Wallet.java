package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Wallet extends AppCompatActivity
{

    ListView icon, name, money;

    String[] values = new String[]{
            "現金",
            "悠遊卡",
            "郵局"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet);

        name = (ListView) findViewById(R.id.name);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        // 使用 ListAdapter 來顯示錢包名稱
        name.setAdapter(adapter);
    }

    // 點選 "+" 開啟新增錢包頁面
    public void btn_add(View v)
    {
        Intent it = new Intent(this, AddWallet.class);
        startActivity(it);
    }


}