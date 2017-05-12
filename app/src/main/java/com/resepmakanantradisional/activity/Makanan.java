package com.resepmakanantradisional.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.resepmakanantradisional.R;
import com.resepmakanantradisional.adapter.AdapterMakanan;
import com.resepmakanantradisional.database.Database;
import com.resepmakanantradisional.util.RecyclerTouchListener;

public class Makanan extends AppCompatActivity {
    public static int ID = 1000;
    private int idprovinsi;

    private RecyclerView recyclerView;

    private Database makananOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        setupData();

        setupToolbar();

        setupComponent();
    }

    private void setupData() {
        idprovinsi = getIntent().getIntExtra(getString(R.string.put_extra_id_provinsi), 0);
    }

    private void setupComponent() {
        makananOpenHelper = new Database(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        AdapterMakanan adapterMakanan = null;

        if (idprovinsi == 0) {
            adapterMakanan = new AdapterMakanan(this, makananOpenHelper.selectAllMakanan());
        } else {
            adapterMakanan = new AdapterMakanan(this, makananOpenHelper.selectAllMakananByProvinsi(idprovinsi));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_makanan);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMakanan);

        final AdapterMakanan finalAdapterMakanan = adapterMakanan;
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int posititon) {
                Intent detailmakanan = new Intent(Makanan.this, DetailMakanan.class);
                detailmakanan.putExtra(getString(R.string.put_extra_id_makanan), finalAdapterMakanan.getMakanan(posititon).getIdMakanan());
                Makanan.this.startActivityForResult(detailmakanan, DetailMakanan.ID);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setupToolbar() {
        setTitle("Makanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
