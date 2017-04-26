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


    public menuAdapter(Context context, String[] dataSplit, List<String> descs) {
        this.mdataSplit = dataSplit;
        this.mContext = context;
        this.mDescs = descs;

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

                        gotoActivity(mdataSplit[0]);
                    }
                });
                break;
            case 1:
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gotoActivity(mdataSplit[1]);
                    }
                });
                break;
            case 2:
                holder.item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gotoActivity(mdataSplit[2]);
                    }
                });
                break;
        }

        return convertView;
    }

    private void gotoActivity(String string) {
        Intent intent = new Intent();
        intent.setClass(mContext, OutputActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", string);

        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    static class ViewHolder {

        TextView item_text;
    }

}
