package com.cena.john.rfid;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    private NfcAdapter mAdapter;
    private String[][] techList;
    private IntentFilter[] intentFilters;
    private PendingIntent pendingIntent;
    private String xyz;
    private String abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mAdapter = NfcAdapter.getDefaultAdapter(this);

        techList = new String[][]{
                new String[]{android.nfc.tech.NfcV.class.getName()},
                new String[]{android.nfc.tech.NfcA.class.getName()}};
        intentFilters = new IntentFilter[]{new IntentFilter(
                NfcAdapter.ACTION_TECH_DISCOVERED),};

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters,
                techList);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        byte[] id = tag.getId();

        if (rawMsgs != null) {
            NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
            for (int i = 0; i < rawMsgs.length; i++) {
                msgs[i] = (NdefMessage) rawMsgs[i];
            }
            NdefMessage msg = msgs[0];
            try {

                xyz = new String(msg.getRecords()[0].getPayload(), "UTF-8");
                xyz = xyz.substring(3);
                abc =  getHexString(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        new Handler(this.getMainLooper()).post(new Runnable() {
            public void run() {
                Intent dataIntent = new Intent();
                dataIntent.setClass(MainActivity.this, menuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("params", xyz);
                bundle.putString("params2", abc);
                dataIntent.putExtras(bundle);
                MainActivity.this.startActivity(dataIntent);
            }
        });
    }

    public static String getHexString(byte[] b)  {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}


