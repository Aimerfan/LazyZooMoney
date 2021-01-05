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

import edu.fcumselab.lazyzoomoney.dbhelper.AccountTable;
import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;

public class AddGroup extends AppCompatActivity {


    GroupTable group;
    SQLiteDatabase db, account_db;
    AccountTable account;
    EditText member1, member2, member3, member4, groupid;
    Toast tos;
    String username_login = null;
    TextView test_txv;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group);

        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("account") != null)
            username_login = bundle.getString("account");

        member1 = (EditText) findViewById(R.id.member1);
        member2 = (EditText) findViewById(R.id.member2);
        member3 = (EditText) findViewById(R.id.member3);
        member4 = (EditText) findViewById(R.id.member4);
        groupid = (EditText) findViewById(R.id.groupid);
        test_txv = (TextView) findViewById(R.id.txv_username);

        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);


        group = new GroupTable(this);
        db = group.db;

        account = new AccountTable(this);
        account_db = account.db;


    }

    public int SearchAccount(String member) // 判斷輸入是否為已註冊帳號
    {
        String member_list[] = member.split(","); // 成員名單，利用 , 分割 member
        Cursor c = account_db.rawQuery("SELECT * FROM " + AccountTable.TB_NAME, null); // 開啟 Account 資料表
        int flag = 1;
        int[] temp; // x is a reference to int[]
        temp = new int[10];

        if(member_list[0].equals("null")) // 至少填寫一名成員
        {
            tos.setText("須至少填一名成員");
            tos.show();
            flag = 0;
        }
        else
        {

            if(c != null && c.moveToFirst())
            {
                for(int i = 0; i < member_list.length; i++) // 判斷成員名單是否都有註冊
                {
                    temp[i] = 0;
                    do{

                        if(c.getString(0).equals(member_list[i]))
                        {
                            temp[i] = 1;
                            break;
                        }
                    }while(c.moveToNext());
                    c.moveToFirst();
                }
            }
            for(int i = 0; i < member_list.length; i++) // 找出未註冊成員
            {
                if(temp[i] == 0)
                {
                    int temp_i = i + 1;
                    tos.setText("無 member" + temp_i + " 帳號");
                    tos.show();
                    flag = 0;
                    break;
                }
            }

        }

        return flag;
    }

    public void groupbtn(View v) // 新增團體按鈕
    {
        int flag = 0;
        int insert_flag = 0;
        if(groupid.getText().toString().isEmpty())
        {
            tos.setText("團體名稱為必填欄位");
            tos.show();
        }
        else
            flag = 2;
        if(flag == 2)
        {
            String member = null; // 將所有填寫的 EditText 存成字串
            if(!("".equals(member1.getText().toString().trim())))  // "".equals(member1.getText().toString().trim()) 判斷EditText是否為空
                member = member1.getText().toString();
            if(!("".equals(member2.getText().toString().trim())))
                member = member + "," + member2.getText().toString();
            if(!("".equals(member3.getText().toString().trim())))
                member = member + "," + member3.getText().toString();
            if(!("".equals(member4.getText().toString().trim())))
                member = member + "," + member4.getText().toString();
            //member = member1.getText().toString() + "," + member2.getText().toString() + "," + member3.getText().toString() + "," + member4.getText().toString();
            member = member + "," + username_login;
            insert_flag = SearchAccount(member); // 判斷成員是否註冊

            if(insert_flag == 1) // 註冊成功
            {
                group.insert(groupid.getText().toString(), username_login, member);
                Intent it = new Intent(this, Group.class);
                it.putExtra("account", username_login);
                startActivity(it);
            }


        }



    }
}
