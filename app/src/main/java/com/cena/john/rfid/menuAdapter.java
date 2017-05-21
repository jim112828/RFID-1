package com.cena.john.rfid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zack on 2016/12/28.
 */

public class menuAdapter extends BaseAdapter {

    private String[] mdataSplit;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDescs;
    private String uid;


    public menuAdapter(Context context, String[] dataSplit, List<String> descs,String uid) {
        this.mdataSplit = dataSplit;
        this.mContext = context;
        this.mDescs = descs;
        this.uid = uid;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mdataSplit.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_item, null);
            holder = new ViewHolder();
            holder.item_text = (TextView) convertView.findViewById(R.id.textTV);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.item_text.setText(mDescs.get(position));

        switch (position) {
            case 0:
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gotoActivity(mdataSplit[0],uid);
                    }
                });
                break;
            case 1:
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gotoActivity(mdataSplit[1],uid);
                    }
                });
                break;
            case 2:
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gotoActivity(mdataSplit[2],uid);
                    }
                });

                break;
        }

        return convertView;
    }

    private void gotoActivity(String string,String uid) {
        Intent intent = new Intent();
        intent.setClass(mContext, OutputActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", string);
        bundle.putString("uid",uid);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    static class ViewHolder {

        TextView item_text;
    }

}
