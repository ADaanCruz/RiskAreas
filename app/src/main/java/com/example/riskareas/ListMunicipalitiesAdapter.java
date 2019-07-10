package com.example.riskareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.riskareas.model.Municipality;

import java.util.ArrayList;

public class ListMunicipalitiesAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context context;
    private ArrayList<Municipality> list;
    private boolean admin;

    public ListMunicipalitiesAdapter(Context context, ArrayList<Municipality> list, boolean admin) {
        this.context = context;
        this.list = list;
        this.admin = admin;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.item_municipality_list, null);
        final Municipality municipio = (Municipality) getItem(position);
        TextView nombre = view.findViewById(R.id.listM_name);
        nombre.setText(municipio.getName());
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
