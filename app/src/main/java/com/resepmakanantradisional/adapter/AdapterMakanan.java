package com.resepmakanantradisional.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;
import com.resepmakanantradisional.R;
import com.resepmakanantradisional.model.Makanan;

import java.util.ArrayList;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolderMakanan> {
    private Context context;
    private ArrayList<Makanan> makanans = new ArrayList<>();

    public AdapterMakanan(Context context, ArrayList<Makanan> makanans) {
        this.context = context;
        this.makanans = makanans;
    }

    public ArrayList<Makanan> getMakanans() {
        return makanans;
    }

    public  void setFilter(ArrayList<Makanan> makanans){
        this.makanans = makanans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderMakanan onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderMakanan(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_makanan, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolderMakanan holder, int position) {
        final Makanan makanan = makanans.get(position);

        Base64Image.with(context).decode(makanan.getGambar()).into(new RequestDecode.Decode() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                holder.imageView.setImageBitmap(bitmap);
                holder.textView.setText(makanan.getNamaMakanan());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return makanans.size();
    }

    public Makanan getMakanan(int posititon) {
        return makanans.get(posititon);
    }

    public static class ViewHolderMakanan extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolderMakanan(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.nama_makanan);
            imageView = (ImageView) itemView.findViewById(R.id.image_makanan);
        }
    }
}
