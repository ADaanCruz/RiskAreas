package com.example.riskareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.riskareas.model.Area;

import java.util.ArrayList;

public class ListAreasAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<Area> list;
    private boolean admin;

    public ListAreasAdapter(Context context, ArrayList<Area> list, boolean admin) {
        this.context = context;
        this.list = list;
        this.admin = admin;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.item_area_list, null);
        final Area municipio = (Area) getItem(position);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
