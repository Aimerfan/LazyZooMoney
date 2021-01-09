package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GroupLedger extends AppCompatActivity {

    String GroupName = "", username_login = null;;
    TextView GroupTxv;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_ledger);

        GroupTxv = (TextView) findViewById(R.id.GroupID);

        Bundle bundle = getIntent().getExtras(); // 利用 Intent 傳遞登入者名稱
        if(bundle.getString("group")!= null)
            username_login = bundle.getString("account");
        if(bundle.getString("group")!= null)
        {
            GroupName = bundle.getString("group");
            GroupTxv.setText(GroupName);
        }


    }

    public void BackToGroup(View v)
    {
        Intent it = new Intent(this, Group.class);
        it.putExtra("account", username_login);
        startActivity(it);
    }
}
