package com.resepmakanantradisional.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.resepmakanantradisional.R;
import com.resepmakanantradisional.model.Provinsi;

import java.util.ArrayList;

public class AdapterProvinsi extends RecyclerView.Adapter<AdapterProvinsi.ProvinsiViewAdapter> {
    public ArrayList<Provinsi> provinsis = null;


    @Override
    public ProvinsiViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProvinsiViewAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_provinsi, parent, false));
    }

    @Override
    public void onBindViewHolder(ProvinsiViewAdapter holder, int position) {
        final Provinsi provinsi = provinsis.get(position);

        holder.nama.setText(provinsi.getNamaProvinsi());
    }

    @Override
    public int getItemCount() {
        return provinsis.size();
    }

    public static class ProvinsiViewAdapter extends RecyclerView.ViewHolder {

        public TextView nama;

        public ProvinsiViewAdapter(View itemView) {
            super(itemView);


            nama = (TextView) itemView.findViewById(R.id.title_adapater);
        }
    }

}
