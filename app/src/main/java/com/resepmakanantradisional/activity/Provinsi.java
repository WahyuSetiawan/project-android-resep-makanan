package com.resepmakanantradisional.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.resepmakanantradisional.R;
import com.resepmakanantradisional.adapter.AdapterProvinsi;
import com.resepmakanantradisional.database.Database;
import com.resepmakanantradisional.util.RecyclerTouchListener;

public class Provinsi extends AppCompatActivity {

    public static final int ID_ACTIVITY_PROVINSI = 12345;

    private RecyclerView recyclerView;

    private Database provinsiOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi);

        setToobar();

        setupComponent();
    }

    private void setupComponent() {
        provinsiOpenHelper = new Database(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_provinsi);

        final AdapterProvinsi adapterProvinsi = new AdapterProvinsi();
        adapterProvinsi.provinsis = provinsiOpenHelper.selectAllProvinsi();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterProvinsi);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int posititon) {
                Intent intent = new Intent(Provinsi.this, Makanan.class);
                intent.putExtra(getString(R.string.put_extra_id_provinsi), adapterProvinsi.provinsis.get(posititon).getIdProvinsi());
                Provinsi.this.startActivityForResult(intent, Makanan.ID);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setToobar() {
        setTitle("Provinsi");
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
