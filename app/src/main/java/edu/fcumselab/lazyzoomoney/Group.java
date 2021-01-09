package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;

public class Group extends AppCompatActivity {

    String username_login = null;
    SQLiteDatabase db;
    TextView txv;
    GroupTable grouptable;
    Spinner group_spinner;
    List<String> group_list = new ArrayList<String>();
    String temp_choose = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        txv = (TextView) findViewById(R.id.txv);
        group_spinner = (Spinner) findViewById(R.id.group_spinner);

        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("account") != null)
            username_login = bundle.getString("account");

        /*Resources res = getResources();
        String[] group_list = res.getStringArray(R.array.Group_arr);*/

        group_list.add("請選擇團體");
        ArrayAdapter adapter_group = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, group_list);

        group_spinner.setAdapter(adapter_group);
        group_spinner.setSelection(group_list.size() - 1, true);
        group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp_choose = parent.getSelectedItem().toString();
                JumpToChoose(temp_choose);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        grouptable = new GroupTable(this);
        db = grouptable.db;
        txv.setText(username_login);
        showLog();

    }
    public void JumpToChoose(String choose)
    {
        if(!choose.equals("請選擇團體"))
        {
            Intent it = new Intent(this, GroupLedger.class);
            it.putExtra("group", choose);
            it.putExtra("account", username_login);
            startActivity(it);
        }
    }

    public void btn_addgroup(View v) // 新增團體帳本頁面跳轉
    {
        Intent it = new Intent(this, AddGroup.class);
        it.putExtra("account", username_login);
        startActivity(it);

    }

    private void showLog() { // 查看團體帳本(僅登入者為成員之帳本)

        Cursor c = db.rawQuery("SELECT * FROM " + GroupTable.TB_NAME, null);
        //c.moveToFirst();
        if (c.moveToFirst()) {
            //String str = "總共有" + c.getCount() + "筆資料\n";
            String str = "";


            do {
                int flag = 0;
                String temp[] = c.getString(2).split(",");
                for(int i = 0; i < temp.length; i++) // 判斷是否為登入者的帳本
                {
                    if(temp[i].equals(username_login))
                        flag = 1;
                }
                if(flag == 1)
                {
                    str += "GroupLedgerID: " + c.getString(0) + "\n";
                    str += "Manager: " + c.getString(1) + "\n";
                    str += "UserID: " + c.getString(2) + "\n";
                    group_list.add(c.getString(0));
                }

            } while (c.moveToNext());

            txv.setText(str);
        }
    }


}
