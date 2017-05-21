package com.resepmakanantradisional.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.resepmakanantradisional.R;
import com.resepmakanantradisional.adapter.AdapterMakanan;
import com.resepmakanantradisional.database.Database;
import com.resepmakanantradisional.util.RecyclerTouchListener;

import java.util.ArrayList;

public class Makanan extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static int ID = 1000;
    private int idprovinsi;

    private RecyclerView recyclerView;
    private ArrayList<com.resepmakanantradisional.model.Makanan> makananArrayList;

    private Database makananOpenHelper;

    AdapterMakanan adapterMakanan = null;

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

        if (idprovinsi == 0) {
            makananArrayList = makananOpenHelper.selectAllMakanan();
        } else {
            makananArrayList = makananOpenHelper.selectAllMakananByProvinsi(idprovinsi);
        }

        adapterMakanan = new AdapterMakanan(this, makananArrayList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_makanan);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMakanan);

        setOnClickListenerRecycler();
    }

    private void setOnClickListenerRecycler() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_bar_search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        ArrayList tmp = new ArrayList();

        for (com.resepmakanantradisional.model.Makanan makanan : makananArrayList) {
            if (makanan.getNamaMakanan().contains(newText)) {
                tmp.add(makanan);
            }
        }

        adapterMakanan.setFilter(tmp);

        return false;
    }
}
