package com.cena.john.rfid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zack on 2016/12/28.
 */

public class menuActivity extends AppCompatActivity {

    private String Params;
    private String[] dataSplit;
    private List<String> descs;
    private ListView listView;
    private menuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        getBundle();

        init();
    }


    private void getBundle() {
        Intent intent = this.getIntent();

        if (intent.getExtras() != null) {
            Params = intent.getExtras().getString("params");
        }
        dataSplit = Params.split("#");

        for (int i = 0; i < dataSplit.length; i++)
            Log.d("getRfidData", dataSplit[i]);

    }

    private void init() {

        listView = (ListView) findViewById(R.id.menuLV);
        descs = new ArrayList<>();
        descs.clear();

        descs.add("搜救模式");
        descs.add("逃生模式");
        descs.add("導覽資訊");

        mAdapter = new menuAdapter(this, dataSplit, descs);

        listView.setAdapter(mAdapter);

    }
}
