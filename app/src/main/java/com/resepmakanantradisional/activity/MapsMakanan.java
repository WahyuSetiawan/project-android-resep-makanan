package com.resepmakanantradisional.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.resepmakanantradisional.R;
import com.resepmakanantradisional.database.Database;
import com.resepmakanantradisional.model.*;

public class MapsMakanan extends AppCompatActivity implements OnMapReadyCallback {

    public static int ID = 2000;

    private com.resepmakanantradisional.model.Provinsi provinsi;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_makanan);

        setupData();

        setupToolbar();

        setupComponent();
    }

    private void setupComponent() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupData() {
        database = new Database(this);

        provinsi = database.selectProvinsi(getIntent().getIntExtra(getString(R.string.put_extra_id_provinsi), 0));
    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(provinsi.getNamaProvinsi());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(provinsi.getLatitude(), provinsi.getLongitude()))
                        .title("marker")
        );

        googleMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(
                                new LatLng(provinsi.getLatitude(), provinsi.getLongitude()),
                                10)
        );
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
