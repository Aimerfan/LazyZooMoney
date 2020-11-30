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

public class Login extends AppCompatActivity {

    EditText username, password;
    Toast tos2;
    TextView txv;
    String username_login = "";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tos2 = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        txv = (TextView) findViewById(R.id.txv);

        db = new AccountTable(this).db;
        showAccount();
    }

    public void loginbutton(View v)
    {
        int flag = 0;
        Cursor c = db.rawQuery("SELECT * FROM " + "Account", null);
        String password_temp = "";
        if(c.moveToFirst())
        {
            do{
                if(c.getString(0).equals(username.getText().toString()))
                {
                    username_login = c.getString(0);
                    password_temp = c.getString(1);
                    flag = 1;
                    break;
                }
                else
                {
                    tos2.setText("無此帳號");
                    tos2.show();
                }
            }while(c.moveToNext());
        }

        if(flag == 1)
        {
            if(password_temp.equals(password.getText().toString()))
            {
                tos2.setText("登入成功");
                tos2.show();
                flag = 5;
            }
            else
            {
                tos2.setText("密碼錯誤");
                tos2.show();
            }
        }
        if(flag == 5)
        {
            Intent it = new Intent(this, PersonalLedger.class);
            it.setClass(Login.this, PersonalLedger.class);
            it.putExtra("使用者", username_login);

            startActivity(it);
            db.close();

        }
    }

    public void gotoregistered(View v)
    {
        Intent it = new Intent(this, Register.class);
        startActivity(it);
    }

    private void showAccount() {
        Cursor c = db.rawQuery("SELECT * FROM " + "Account", null);
        if (c.moveToFirst()) {
            String str = "總共有" + c.getCount() + "筆資料\n";
            str += "-----\n";

            do {
                str += "account: " + c.getString(0) + "\n";
                str += "password: " + c.getString(1) + "\n";
                str += "email: " + c.getString(2) + "\n";
            } while (c.moveToNext());

            txv.setText(str);
        }
    }
}
