package edu.fcumselab.lazyzoomoney;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }

    // 點選 "登入" 開啟登入頁
    public void btn_login(View v)
    {
        Intent it = new Intent(this, Login.class);
        startActivity(it);
    }

    // 點選 "註冊" 開啟註冊頁
    public void btn_register(View v)
    {
        Intent it = new Intent(this, Register.class);
        startActivity(it);
    }

    // 點選 "錢包" 開啟我的錢包 (測試用)
    public void btn_wallet(View v)
    {
        Intent it = new Intent(this, Wallet.class);
        startActivity(it);
    }

    // 點選 "新增錢包" 開啟新增錢包頁面 (測試用)
    public void btn_addwallet(View v)
    {
        Intent it = new Intent(this, AddWallet.class);
        startActivity(it);
    }
}