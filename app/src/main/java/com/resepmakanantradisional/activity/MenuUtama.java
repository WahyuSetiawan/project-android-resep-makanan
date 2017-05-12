package com.resepmakanantradisional.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.resepmakanantradisional.R;

public class MenuUtama extends AppCompatActivity {
    private static final int ID_ACTIVITY_MENU_UTAMA = 47585;

    private Button aboutme, provinsi, makanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        setupToolbar();

        setupComponent();
    }

    private void setupComponent() {
        provinsi = (Button) findViewById(R.id.menu_utama_button_provinsi);
        aboutme = (Button) findViewById(R.id.menu_utama_button_about_me);
        makanan = (Button) findViewById(R.id.menu_utama_button_makanan);

        provinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent provinsi = new Intent(MenuUtama.this, Provinsi.class);

                MenuUtama.this.startActivityForResult(provinsi, Provinsi.ID_ACTIVITY_PROVINSI);
            }
        });

        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutme = new Intent(MenuUtama.this, AboutMe.class);

                MenuUtama.this.startActivityForResult(aboutme, AboutMe.ID_ACTIVITY);
            }
        });

        makanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makanan = new Intent(MenuUtama.this, Makanan.class);

                makanan.putExtra(getString(R.string.put_extra_id_provinsi), 0);
                MenuUtama.this.startActivityForResult(makanan, Makanan.ID);

            }
        });
    }

    private void setupToolbar() {
        setTitle("Menu Utama");
    }
}
