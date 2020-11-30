package edu.fcumselab.lazyzoomoney.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AccountTable
{
    // 資料表名稱, 實作時必須填寫
    public static final String TB_NAME = "Account";

    // SQLite PK name, 不能動!!!
    public static final String PK_ID = "_id";

    // 列出所有 table columns
    private static final String ACCOUNT_COLUMN = "account";
    private static final String PASSWD_COLUMN = "password";
    private static final String EMAIL_COLUMN = "email";

    // 建表語句, 要定義清楚
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" +
                    ACCOUNT_COLUMN + " VARCHAR(16) NOT NULL," +
                    PASSWD_COLUMN + " VARCHAR(16) NOT NULL," +
                    EMAIL_COLUMN + " VARCHAR(64) NOT NULL" +
                    ");";

    public SQLiteDatabase db;

    public AccountTable(Context context)
    {
        db = new DBHelper(context).getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }

    protected void finalize()
    {
        db.close();
    }

    public void insert(String account, String password, String email)
    {
        ContentValues cv = new ContentValues();
        cv.put(ACCOUNT_COLUMN, account);
        cv.put(PASSWD_COLUMN, password);
        cv.put(EMAIL_COLUMN, email);

        db.insert(TB_NAME, null, cv);
    }
}

