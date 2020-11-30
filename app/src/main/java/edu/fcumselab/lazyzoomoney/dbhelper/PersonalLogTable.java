package edu.fcumselab.lazyzoomoney.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PersonalLogTable
{
    // 資料表名稱, 實作時必須填寫
    public static final String TB_NAME = "Personal_Log";

    // SQLite PK name, 不能動!!!
    public static final String PK_ID = "_id";

    // 列出所有 table columns
    private static final String MONEY_COLUMN = "Money";
    private static final String ITEM_COLUMN = "Item";
    private static final String WALLET_COLUMN = "Wallet";
    private static final String LEDGER_COLUMN = "Ledger";
    private static final String CATEGORY_COLUMN = "Category";

    // 建表語句, 要定義清楚
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" +
                    MONEY_COLUMN + " INTEGER," +
                    ITEM_COLUMN + " VARCHAR(10)," +
                    WALLET_COLUMN + " VARCHAR(10)," +
                    LEDGER_COLUMN + " VARCHAR(5)," +
                    CATEGORY_COLUMN + " VARCHAR(10)" +
                    ");";

    public SQLiteDatabase db;

    public PersonalLogTable(Context context)
    {
        db = new DBHelper(context).getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }

    protected void finalize()
    {
        db.close();
    }

    public void insert()
    {
    }
}
