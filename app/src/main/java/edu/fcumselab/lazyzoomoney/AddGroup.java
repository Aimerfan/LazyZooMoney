package edu.fcumselab.lazyzoomoney;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;


import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;

public class AddGroup extends AppCompatActivity {


    GroupTable group;
    SQLiteDatabase db;
    EditText member1, member2, member3, member4, groupid;
    Toast tos;
    String username_login = null;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("account") != null)
            username_login = bundle.getString("account");

        member1 = (EditText) findViewById(R.id.member1);
        member2 = (EditText) findViewById(R.id.member2);
        member3 = (EditText) findViewById(R.id.member3);
        member4 = (EditText) findViewById(R.id.member4);
        groupid = (EditText) findViewById(R.id.groupid);

        tos = Toast.makeText(this, "", Toast.LENGTH_SHORT);



        group = new GroupTable(this);
        db = group.db;

    }

    public void groupbtn(View v)
    {
        String member = null;

        if(!("".equals(member1.getText().toString().trim())))  // "".equals(member1.getText().toString().trim()) 判斷EditText是否為空
            member = member1.getText().toString();
        if(!("".equals(member2.getText().toString().trim())))
            member = member + "," + member2.getText().toString();
        if(!("".equals(member3.getText().toString().trim())))
            member = member + "," + member3.getText().toString();
        if(!("".equals(member4.getText().toString().trim())))
            member = member + "," + member4.getText().toString();
        //member = member1.getText().toString() + "," + member2.getText().toString() + "," + member3.getText().toString() + "," + member4.getText().toString();
        member = member + "," + username_login;
        group.insert(groupid.getText().toString(), username_login, member);

        Intent it = new Intent(this, Group.class);
        it.putExtra("account", username_login);
        startActivity(it);
    }
}
