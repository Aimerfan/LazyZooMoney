package edu.fcumselab.lazyzoomoney.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroupTable{

    // 資料表名稱, 實作時必須填寫
    public static final String TB_NAME = "Grouptable";

    // SQLite PK name, 不能動!!!
    public static final String PK_ID = "_id";

    // 列出所有 table columns
    private static final String GroupLedgerID = "GroupLedgerID";
    private static final String UserID = "UserID";


    // 建表語句, 要定義清楚
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" +
                    GroupLedgerID + " VARCHAR(10)," +
                    UserID + " VARCHAR(10)" +  ");";

    public SQLiteDatabase db;

    public GroupTable(Context context)
    {
        db = new DBHelper(context).getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }

    protected void finalize()
    {
        db.close();
    }

    public void insert(String GroupLedgerID, String UserID)
    {
        ContentValues cv = new ContentValues();
        cv.put(GroupLedgerID, GroupLedgerID);
        cv.put(UserID, UserID);

        db.insert(TB_NAME, null, cv);
    }

}
