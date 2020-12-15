package edu.fcumselab.lazyzoomoney;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edu.fcumselab.lazyzoomoney.dbhelper.GroupTable;


import edu.fcumselab.lazyzoomoney.dbhelper.PersonalLogTable;

public class AddGroup extends AppCompatActivity {


    GroupTable group;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group);

    }
}
