package edu.fcumselab.lazyzoomoney.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class WalletTable
{
    // 資料表名稱, 實作時必須填寫
    public static final String TB_NAME = "Wallet";

    // SQLite PK name, 不能動!!!
    public static final String PK_ID = "_id";

    // 列出所有 table columns
    private static final String KIND_COLUMN = "kind";
    private static final String NAME_COLUMN = "name";
    private static final String MONEY_COLUMN = "money";
    private static final String COMMENT_COLUMN = "comment";

    // 建表語句, 要定義清楚
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" +
                    KIND_COLUMN + " CHAR(10) NOT NULL," +
                    NAME_COLUMN + " CHAR(30) NOT NULL," +
                    MONEY_COLUMN + " INT NOT NULL," +
                    COMMENT_COLUMN + " VARCHAR(256)" +
                    ");";

    private SQLiteDatabase db;

    public WalletTable(Context context)
    {
        db = new DBHelper(context).getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }

    protected void finalize()
    {
        db.close();
    }

    public long insert(String kind, String name, int money, String comment)
    {
        ContentValues cv = new ContentValues();
        cv.put(KIND_COLUMN, kind);
        cv.put(NAME_COLUMN, name);
        cv.put(MONEY_COLUMN, money);
        if(comment != null)
        {
            cv.put(COMMENT_COLUMN, comment);
        }
        long result = db.insert(TB_NAME, null, cv);

        return result;
    }
}
