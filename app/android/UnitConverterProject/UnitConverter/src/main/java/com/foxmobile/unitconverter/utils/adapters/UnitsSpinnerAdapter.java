package com.foxmobile.unitconverter.utils.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foxmobile.unitconverter.utils.converters.ConverterBase;
import com.foxmobile.unitconverter.utils.converters.ConvertersManager;

import java.util.ArrayList;

/**
 * Created by Azriel on 1/10/14.
 */
public class UnitsSpinnerAdapter extends ArrayAdapter<Pair<ConverterBase.eUnits, String>> {

    private final LayoutInflater mInflater;

    private static class ViewHolder {
        TextView tvConverterName;
    }


    public UnitsSpinnerAdapter(Context context, ConvertersManager converters) {
        super(context, android.R.layout.simple_spinner_item, converters.getCurrConverterUnits());

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return _getView(android.R.layout.simple_spinner_item, position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        return _getView(android.R.layout.simple_spinner_dropdown_item, position, convertView, parent);
    }

    private View _getView(int viewLayoutId, int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(viewLayoutId, parent, false);

            holder = new ViewHolder();
            holder.tvConverterName = (TextView)convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvConverterName.setText(this.getItem(position).second);

        return convertView;
    }

//    @Override
//    public long getItemId(int position) {
//        return this.getItem(position).first;
//    }
}
