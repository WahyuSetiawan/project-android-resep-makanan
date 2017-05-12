package com.resepmakanantradisional.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestDecode;
import com.resepmakanantradisional.R;
import com.resepmakanantradisional.database.Database;

import java.util.Locale;

public class DetailMakanan extends AppCompatActivity {

    public static int ID = 456;

    private int idMakanan;
    private com.resepmakanantradisional.model.Makanan makanan;
    private ImageView mImageMakanan;
    private TextView mTextNamaMakanan, mTextHargaMakanan, mTextProvinsiMakanan, mTextDeskripsiMakanan, mTextCaraMemasakMakanan;
    private Button mButtonPeta;
    private TabHost mTabHostMakanan;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);

        setupData();

        setupToolbar();

        setupComponent();
    }

    private void setupComponent() {
        mImageMakanan = (ImageView) findViewById(R.id.image_makanan);
        mTextHargaMakanan = (TextView) findViewById(R.id.harga_makanan);
        mTextNamaMakanan = (TextView) findViewById(R.id.nama_makanan);
        mTextProvinsiMakanan = (TextView) findViewById(R.id.provinsi_makanan);
        mTextDeskripsiMakanan = (TextView) findViewById(R.id.deskripsi_makanan);
        mTextCaraMemasakMakanan = (TextView) findViewById(R.id.cara_memasak_makanan);
        mButtonPeta = (Button) findViewById(R.id.peta);

        mTabHostMakanan = (TabHost) findViewById(R.id.tabHost);

        mTabHostMakanan.setup();

        TabHost.TabSpec mTabDeskripsiMakanan = mTabHostMakanan.newTabSpec(getString(R.string.tab_dekripsi_makanan));
        mTabDeskripsiMakanan.setContent(R.id.tab_deskripsi_makanan);
        mTabDeskripsiMakanan.setIndicator(getString(R.string.tab_dekripsi_makanan));
        mTabHostMakanan.addTab(mTabDeskripsiMakanan);

        TabHost.TabSpec mTabCaraMemasak = mTabHostMakanan.newTabSpec(getString(R.string.tab_cara_memasak_makanan));
        mTabCaraMemasak.setContent(R.id.tab_cara_memasak);
        mTabCaraMemasak.setIndicator(getString(R.string.tab_cara_memasak_makanan));
        mTabHostMakanan.addTab(mTabCaraMemasak);


        mTextHargaMakanan.setText(mTextHargaMakanan.getText() + String.valueOf(makanan.getHarga()));
        mTextProvinsiMakanan.setText(mTextProvinsiMakanan.getText() + makanan.getProvinsi().getNamaProvinsi());
        mTextNamaMakanan.setText(mTextNamaMakanan.getText() + makanan.getNamaMakanan());
        mTextDeskripsiMakanan.setText(makanan.getDekripsiMakanan());
        mTextCaraMemasakMakanan.setText(makanan.getCaraMemasak());

        mButtonPeta.setText(mButtonPeta.getText() + makanan.getProvinsi().getNamaProvinsi());

        Base64Image.with(this).decode(makanan.getGambar()).into(new RequestDecode.Decode() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                try {
                    mImageMakanan.setImageBitmap(bitmap);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure() {

            }
        });

        mButtonPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMakanan.this, MapsMakanan.class);
                intent.putExtra(getString(R.string.put_extra_id_provinsi), makanan.getProvinsi().getIdProvinsi());
                DetailMakanan.this.startActivityForResult(intent, MapsMakanan.ID);
            }
        });
    }

    private void setupToolbar() {
        setTitle(makanan.getNamaMakanan());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupData() {
        idMakanan = getIntent().getIntExtra(getString(R.string.put_extra_id_makanan), 0);
        database = new Database(this);

        makanan = database.selectMakanan(idMakanan);
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
