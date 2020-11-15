package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Wallet extends AppCompatActivity
{
    private List<WalletEntity>walletList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet);

        initWallets();  // 將資料表wallet內容匯入
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        /* LinearLayoutManager 中制定了可擴展的布局排列接口,
           子類按照接口中的規範來實作, 就可以制定出不同排版方式的布局
         */

        // 配置布局, 默認為 vertical (垂直布局 上下捲動)，下面這句可將布局改為水平布局 (左右拉動)
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        WalletAdapter adapter=new WalletAdapter(walletList);
        recyclerView.setAdapter(adapter);
    }

    // 點選 "+" 開啟新增錢包頁面
    public void btn_add(View v)
    {
        Intent it = new Intent(this, AddWallet.class);
        startActivity(it);
    }

    /* 未來實作, 資料表 wallet 匯入的地方
       tip: 剩餘金額 (wallet.money) 需要轉成字串
     */
    private void initWallets()
    {
        for (int i = 1; i < 15; i++)
        {
            WalletEntity wallet = new WalletEntity(R.drawable.money, "現金" + i, "" + i * 100);
            walletList.add(wallet);
        }
    }

}