package edu.fcumselab.lazyzoomoney;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class Wallet extends AppCompatActivity// implements WalletAdapter.OnItemClickListener, WalletAdapter.OnItemLongClickListener
{
    private static final String db_name = "LazyZooMoney.db";
    private static final String tb_name = "Wallet";
    SQLiteDatabase db;

    private final List<WalletEntity>walletList = new ArrayList<>();
    //private RecyclerView mRecyclerView = null;
    //private RecyclerViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS " + tb_name
                + "(Kind CHAR(10)," +
                "Name CHAR(30)," +
                "Money INT," +
                "Comment VARCHAR(256));";
        db.execSQL(sql);

        initWallets();  // 將資料表wallet內容匯入
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        /* LinearLayoutManager 中制定了可擴展的布局排列接口,
           子類按照接口中的規範來實作, 就可以制定出不同排版方式的布局 */

        // 配置布局, 默認為 vertical (垂直布局 上下捲動)，下面這句可將布局改為水平布局 (左右拉動)
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        WalletAdapter adapter = new WalletAdapter(walletList);
        recyclerView.setAdapter(adapter);

        //recyclerView.setOnItemClickListener(adapter);
        //recyclerView.setOnItemLongClickListener(adapter);
    }

    /* 未來修正: 搜尋相對應的 ID
       查詢資料表 Wallet, 顯示所有錢包的資訊 */
    private void initWallets()
    {
        Cursor c = db.rawQuery("SELECT * FROM " + tb_name, null);
        //String sql = "SELECT count(*) from " + tb_name + ";";
        int num = c.getCount(); // 得到 tuples 數量 (?)
        c.moveToFirst();
        // 根據 Wallet.Kind 設定相對應的 iconID
        for (int i = 0; i < num; i++)
        {
            int iconId = 0;
            switch(c.getString(0)) {
                case "現金":
                    iconId = R.drawable.money;
                    break;
                case "卡片":
                    iconId = R.drawable.card;
                    break;
                case "銀行":
                    iconId = R.drawable.bank;
                    break;
                default:
                    iconId = 0;
            }

            WalletEntity wallet = new WalletEntity(iconId, c.getString(1), c.getString(2));
            walletList.add(wallet);
            c.moveToNext();
        }
    }

    // 點選 "+" 開啟新增錢包頁面
    public void btn_add(View v)
    {
        Intent it = new Intent(this, AddWallet.class);
        startActivity(it);
    }
/*
    @Override
    public void onClick(View parent, int position) {
        Toast.makeText(this, "點擊了第" + (position + 1) + "項", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View parent, int position) {
        Toast.makeText(this, "長壓了第" + (position + 1) + "項", Toast.LENGTH_SHORT).show();
        return true;
    }
 */
}