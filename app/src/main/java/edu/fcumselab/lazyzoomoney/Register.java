package edu.fcumselab.lazyzoomoney;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.fcumselab.lazyzoomoney.dbhelper.AccountTable;

public class Register extends AppCompatActivity
{

    AccountTable accounts;
    SQLiteDatabase db;

    EditText usernameR, passwordR, email;
    Toast tos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usernameR = (EditText) findViewById(R.id.usernameR); // 使用者名稱
        passwordR = (EditText) findViewById(R.id.passwordR); // 密碼
        email = (EditText) findViewById(R.id.email); // 信箱
        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT); // 提醒框

        accounts = new AccountTable(this);
        db = accounts.db;

    }

    /*private void addData(String account, String password, String email) // 將資料加入資料表
    {
        ContentValues cv = new ContentValues(3);
        cv.put("account", account);
        cv.put("password", password);
        cv.put("email", email);


        db.insert("Account", null, cv); // 插入資料

    }*/

    public void registerbutton(View v) // 註冊按鈕
    {
        String pattern = "^.*@gmail.com$"; // 判斷是否為信箱格式
        int flag = 0;
        // 搜尋資料表內容
        Cursor c = db.rawQuery("SELECT * FROM " + AccountTable.TB_NAME, null);
        if(usernameR.getText().toString().isEmpty())
        {
            tos.setText("用戶名為必填欄位");
            tos.show();
        }
        else
        {
            if(c.getCount() == 0)
            {
                flag = 1;
            }
            if(c.moveToFirst())
            {
                do{
                    if(c.getString(0).equals(usernameR.getText().toString()))
                    {
                        tos.setText("使用者名稱重複");
                        tos.show();
                        break;
                    }
                    else
                        flag = 1;
                }while(c.moveToPrevious());
            }

        }
        if(flag == 1)
        {
            if(passwordR.getText().toString().isEmpty())
            {
                tos.setText("密碼為必填欄位");
                tos.show();
            }
            else
                flag = 2;
        }
        if(flag == 2)
        {
            if(email.getText().toString().isEmpty())
            {
                tos.setText("電子信箱為必填欄位");
                tos.show();
            }
            else if(!email.getText().toString().matches(pattern))
            {
                tos.setText("電子信箱格式錯誤");
                tos.show();
            }
            else
                flag = 3;
        }
        if(flag == 3)
        {
            //addData(usernameR.getText().toString(), passwordR.getText().toString(), email.getText().toString()); // 加入資料表
            accounts.insert(usernameR.getText().toString(), passwordR.getText().toString(), email.getText().toString());
            c = db.rawQuery("SELECT * FROM " + AccountTable.TB_NAME, null);
            /*if(c.getCount() == 0)
            {
                addData(usernameR.getText().toString(), passwordR.getText().toString(), email.getText().toString());
                c = db.rawQuery("SELECT * FROM " + tb_name, null);
            }

            if(c.moveToFirst())
            {
                String str = "總共有" + c.getCount() + "筆資料\n";
                str += "-----\n";

                do{
                    str += "account: " + c.getString(0) + "\n";
                    str += "password: " + c.getString(1) + "\n";
                    str += "email: " + c.getString(2) + "\n";
                }while(c.moveToNext());
            }*/
            tos.setText("註冊成功");
            tos.show();
            Intent it = new Intent(this, Login.class); // 頁面跳轉
            startActivity(it);
            usernameR.setText(null);
            passwordR.setText(null);
            email.setText(null);
            db.close();
        }

    }

}
