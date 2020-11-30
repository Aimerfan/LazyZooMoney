package edu.fcumselab.lazyzoomoney;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import edu.fcumselab.lazyzoomoney.dbhelper.WalletTable;

public class AddWallet extends AppCompatActivity
{
    EditText nameR, moneyR, commentR;
    Spinner spinnerR;
    WalletTable wallets;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addwallet);
        spinnerR = (Spinner) findViewById(R.id.spinner);
        nameR = (EditText) findViewById(R.id.nameR);
        moneyR = (EditText) findViewById(R.id.moneyR);
        commentR = (EditText) findViewById(R.id.commentR);

        wallets = new WalletTable(this);
    }

    private void clearR() {
        spinnerR.setSelection(1);
        nameR.setText(null);
        moneyR.setText(null);
        commentR.setText(null);
    }

    public void createWallet(View v) {
        String kind = spinnerR.getSelectedItem().toString();
        String name = nameR.getText().toString();
        String money = moneyR.getText().toString();
        String comment = commentR.getText().toString();
        int initMoney;

        if (name.isEmpty() || money.isEmpty())
        {
            return;
        }
        try
        {
            initMoney = Integer.parseInt(money);
        } catch (NumberFormatException e)
        {
            return;
        }

        wallets.insert(kind, name, initMoney, comment);

        clearR();
    }
}
