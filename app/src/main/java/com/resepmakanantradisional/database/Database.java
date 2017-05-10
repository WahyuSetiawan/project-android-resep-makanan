package com.resepmakanantradisional.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestEncode;
import com.resepmakanantradisional.R;
import com.resepmakanantradisional.model.Makanan;
import com.resepmakanantradisional.model.Provinsi;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 27;
    static final String DATABASE_NAME = "databaseresepmakanantradisional";
    Context context;

    public static final String PROVINSI_ID_PROVINSI = "idProvinsi";
    public static final String PROVINSI_NAMA_PROVINSI = "namaProvinsi";
    public static final String PROVINSI_LATITUDE = "latitude";
    public static final String PROVINSI_LONGITUDE = "longitude";

    public static final String TABLE_PROVINSI = "provinsi";

    public static final String MAKANAN_ID_MAKANAN = "idMakanan";
    public static final String MAKANAN_NAMA_MAKANAN = "namaMakanan";
    public static final String MAKANAN_DEKRIPSI_MAKANAN = "deskripsiMakanan";
    public static final String MAKANAN_CARA_MEMASAK = "caraMemasak";
    public static final String MAKANAN_HARGA = "harga";
    public static final String MAKANAN_GAMBAR = "gambar";
    public static final String MAKANAN_ID_PROVINSI = "idProvinsi";

    public static final String TABLE_MAKANAN = "resep";


    private SQLiteDatabase mSQLLite;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PROVINSI + " (" +
                PROVINSI_ID_PROVINSI + " integer primary key autoincrement," +
                PROVINSI_NAMA_PROVINSI + " char(30) not null," +
                PROVINSI_LATITUDE + " text," +
                PROVINSI_LONGITUDE + " text not null" +
                ")");

        db.execSQL("create table " + TABLE_MAKANAN + " (" +
                MAKANAN_ID_MAKANAN + " integer primary key autoincrement," +
                MAKANAN_NAMA_MAKANAN + " char(30) not null," +
                MAKANAN_DEKRIPSI_MAKANAN + " text," +
                MAKANAN_CARA_MEMASAK + " text not null," +
                MAKANAN_HARGA + " integer not null," +
                MAKANAN_GAMBAR + " text not null," +
                MAKANAN_ID_PROVINSI + " integer not null" +
                ")");

        Provinsi provinsi = new Provinsi();
        ContentValues mval = new ContentValues();

        provinsi.setIdProvinsi(1);
        provinsi.setNamaProvinsi("Jogjakarta");
        provinsi.setLatitude((float) -7.78278);
        provinsi.setLongitude((float) 110.36083);

        mval.put(PROVINSI_NAMA_PROVINSI, provinsi.getNamaProvinsi());
        mval.put(PROVINSI_LATITUDE, provinsi.getLatitude());
        mval.put(PROVINSI_LONGITUDE, provinsi.getLongitude());

        db.insert(TABLE_PROVINSI, null, mval);

        provinsi = new Provinsi();
        mval = new ContentValues();

        provinsi.setIdProvinsi(2);
        provinsi.setNamaProvinsi("Semarang");
        provinsi.setLatitude((float) -6.9932);
        provinsi.setLongitude((float) 110.4203);

        mval.put(PROVINSI_NAMA_PROVINSI, provinsi.getNamaProvinsi());
        mval.put(PROVINSI_LATITUDE, provinsi.getLatitude());
        mval.put(PROVINSI_LONGITUDE, provinsi.getLongitude());

        db.insert(TABLE_PROVINSI, null, mval);

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.masakan1)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();

                makanan.setIdMakanan(1);
                makanan.setNamaMakanan("masakan1");
                makanan.setCaraMemasak("masakan1");
                makanan.setDekripsiMakanan("makanan1");
                makanan.setGambar(s);
                makanan.setHarga(100);

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(1);
                makanan.setProvinsi(provinsi);

                ContentValues mval = contentValuesMakanan(makanan);

                insertMakanan(makanan);
            }

            @Override
            public void onFailure() {

            }
        });

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.masakan1)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(2);
                makanan.setNamaMakanan("masakan2");
                makanan.setCaraMemasak("masakan2");
                makanan.setDekripsiMakanan("makanan2");
                makanan.setGambar(s);
                makanan.setHarga(2000);

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(1);
                makanan.setProvinsi(provinsi);

                ContentValues mval = contentValuesMakanan(makanan);

                //db.insert(TABLE_MAKANAN, null, mval);
                insertMakanan(makanan);
            }

            @Override
            public void onFailure() {

            }
        });

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.masakan1)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(3);
                makanan.setNamaMakanan("masakan3");
                makanan.setCaraMemasak("masakan3");
                makanan.setDekripsiMakanan("makanan3");
                makanan.setGambar(s);
                makanan.setHarga(300);

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(2);
                makanan.setProvinsi(provinsi);

                ContentValues mval = contentValuesMakanan(makanan);

                // db.insert(TABLE_MAKANAN, null, mval);
                insertMakanan(makanan);
            }

            @Override
            public void onFailure() {

            }
        });//*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_MAKANAN);
        db.execSQL("drop table if exists " + TABLE_PROVINSI);
        onCreate(db);
    }


    private ContentValues contentValuesProvinsi(Provinsi provinsi) {
        ContentValues mval = new ContentValues();
        mval.put(PROVINSI_ID_PROVINSI, provinsi.getIdProvinsi());
        mval.put(PROVINSI_NAMA_PROVINSI, provinsi.getNamaProvinsi());
        mval.put(PROVINSI_LATITUDE, provinsi.getLatitude());
        mval.put(PROVINSI_LONGITUDE, provinsi.getLongitude());

        return mval;
    }

    public boolean insertProvinsi(Provinsi provinsi) {
        mSQLLite = this.getWritableDatabase();

        ContentValues mval = this.contentValuesProvinsi(provinsi);

        Long i = mSQLLite.insert(TABLE_PROVINSI, null, mval);

        Log.d("Values of I = ", "******************* " + i + " ***************");

        mSQLLite.close();

        if (i == 1) {
            return true;
        }

        return false;
    }

    public boolean editProvinsi(Provinsi provinsi) {
        mSQLLite = this.getWritableDatabase();

        ContentValues mval = contentValuesProvinsi(provinsi);

        return mSQLLite.update(TABLE_PROVINSI, mval, TABLE_PROVINSI + " = " + String.valueOf(provinsi.getIdProvinsi()), null) > 0;
    }

    public boolean deleteProvinsi(String id) {
        mSQLLite = this.getWritableDatabase();

        int returnDeleteInt = mSQLLite.delete(TABLE_PROVINSI, PROVINSI_ID_PROVINSI + " = " + id, null);

        mSQLLite.close();

        return returnDeleteInt > 0;
    }

    public Provinsi selectProvinsi(int id) {
        mSQLLite = this.getWritableDatabase();
        Provinsi provinsi = null;

        Cursor alldata = mSQLLite.query(TABLE_PROVINSI,
                new String[]{
                        PROVINSI_ID_PROVINSI,
                        PROVINSI_NAMA_PROVINSI,
                        PROVINSI_LATITUDE,
                        PROVINSI_LONGITUDE
                }, PROVINSI_ID_PROVINSI + " = " + id, null, null, null, null);

        if (alldata != null) {
            alldata.moveToFirst();
            provinsi = new Provinsi();

            provinsi.setIdProvinsi(alldata.getInt(alldata.getColumnIndex(PROVINSI_ID_PROVINSI)));
            provinsi.setNamaProvinsi(alldata.getString(alldata.getColumnIndex(PROVINSI_NAMA_PROVINSI)));
            provinsi.setLatitude(alldata.getFloat(alldata.getColumnIndex(PROVINSI_LATITUDE)));
            provinsi.setLongitude(alldata.getFloat(alldata.getColumnIndex(PROVINSI_LONGITUDE)));
        }

        mSQLLite.close();

        return provinsi;
    }

    public ArrayList<Provinsi> selectAllProvinsi() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Provinsi> provinsis = new ArrayList<>();

        Cursor alldata = mSQLLite.query(TABLE_PROVINSI,
                new String[]{
                        PROVINSI_ID_PROVINSI,
                        PROVINSI_NAMA_PROVINSI,
                        PROVINSI_LATITUDE,
                        PROVINSI_LONGITUDE
                }, null, null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Provinsi provinsi = new Provinsi();

                provinsi.setIdProvinsi(alldata.getInt(alldata.getColumnIndex(PROVINSI_ID_PROVINSI)));
                provinsi.setNamaProvinsi(alldata.getString(alldata.getColumnIndex(PROVINSI_NAMA_PROVINSI)));
                provinsi.setLatitude(alldata.getFloat(alldata.getColumnIndex(PROVINSI_LATITUDE)));
                provinsi.setLongitude(alldata.getFloat(alldata.getColumnIndex(PROVINSI_LONGITUDE)));

                provinsis.add(provinsi);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return provinsis;
    }


    protected static ContentValues contentValuesMakanan(Makanan makanan) {
        ContentValues mval = new ContentValues();
        mval.put(MAKANAN_ID_MAKANAN, makanan.getIdMakanan());
        mval.put(MAKANAN_NAMA_MAKANAN, makanan.getNamaMakanan());
        mval.put(MAKANAN_DEKRIPSI_MAKANAN, makanan.getDekripsiMakanan());
        mval.put(MAKANAN_CARA_MEMASAK, makanan.getCaraMemasak());
        mval.put(MAKANAN_HARGA, makanan.getHarga());
        mval.put(MAKANAN_GAMBAR, makanan.getGambar());
        mval.put(MAKANAN_ID_PROVINSI, makanan.getProvinsi().getIdProvinsi());

        return mval;
    }

    public boolean insertMakanan(Makanan makanan) {
        mSQLLite = this.getWritableDatabase();

        ContentValues mval = this.contentValuesMakanan(makanan);

        Long i = mSQLLite.insert(TABLE_MAKANAN, null, mval);

        Log.d("Values of I = ", "******************* " + i + " ***************");

        mSQLLite.close();

        if (i == 1) {
            return true;
        }

        return false;
    }

    public boolean editMakanan(Makanan makanan) {

        mSQLLite = this.getWritableDatabase();

        ContentValues mval = contentValuesMakanan(makanan);

        return mSQLLite.update(TABLE_MAKANAN, mval, TABLE_MAKANAN + " = " + String.valueOf(makanan.getIdMakanan()), null) > 0;
    }

    public boolean deleteMakanan(String id) {
        mSQLLite = this.getWritableDatabase();

        int returnDeleteInt = mSQLLite.delete(TABLE_MAKANAN, MAKANAN_ID_MAKANAN + " = " + id, null);

        mSQLLite.close();

        return returnDeleteInt > 0;
    }

    public Makanan selectMakanan(int id) {
        mSQLLite = this.getWritableDatabase();
        Makanan makanan = null;

        Cursor alldata = mSQLLite.query(TABLE_MAKANAN,
                new String[]{
                        MAKANAN_ID_MAKANAN,
                        MAKANAN_NAMA_MAKANAN,
                        MAKANAN_DEKRIPSI_MAKANAN,
                        MAKANAN_CARA_MEMASAK,
                        MAKANAN_HARGA,
                        MAKANAN_GAMBAR,
                        MAKANAN_ID_PROVINSI
                }, MAKANAN_ID_MAKANAN + " = " + id, null, null, null, null);

        if (alldata != null) {
            alldata.moveToFirst();
            makanan = new Makanan();

            makanan.setIdMakanan(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_MAKANAN)));
            makanan.setNamaMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_NAMA_MAKANAN)));
            makanan.setDekripsiMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_DEKRIPSI_MAKANAN)));
            makanan.setCaraMemasak(alldata.getString(alldata.getColumnIndex(MAKANAN_CARA_MEMASAK)));
            makanan.setHarga(alldata.getInt(alldata.getColumnIndex(MAKANAN_HARGA)));
            makanan.setGambar(alldata.getString(alldata.getColumnIndex(MAKANAN_GAMBAR)));

            Provinsi provinsi = this.selectProvinsi(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_PROVINSI)));
            makanan.setProvinsi(provinsi);
        }

        mSQLLite.close();

        return makanan;
    }

    public ArrayList<Makanan> selectAllMakanan() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(TABLE_MAKANAN,
                new String[]{
                        MAKANAN_ID_MAKANAN,
                        MAKANAN_NAMA_MAKANAN,
                        MAKANAN_DEKRIPSI_MAKANAN,
                        MAKANAN_CARA_MEMASAK,
                        MAKANAN_HARGA,
                        MAKANAN_GAMBAR,
                        MAKANAN_ID_PROVINSI
                }, null, null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan();

                makanan.setIdMakanan(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_MAKANAN)));
                makanan.setNamaMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_NAMA_MAKANAN)));
                makanan.setDekripsiMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_DEKRIPSI_MAKANAN)));
                makanan.setCaraMemasak(alldata.getString(alldata.getColumnIndex(MAKANAN_CARA_MEMASAK)));
                makanan.setHarga(alldata.getInt(alldata.getColumnIndex(MAKANAN_HARGA)));
                makanan.setGambar(alldata.getString(alldata.getColumnIndex(MAKANAN_GAMBAR)));

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_PROVINSI)));
                makanan.setProvinsi(provinsi);

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

    public ArrayList<Makanan> selectAllMakananByProvinsi(int id) {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(TABLE_MAKANAN,
                new String[]{
                        MAKANAN_ID_MAKANAN,
                        MAKANAN_NAMA_MAKANAN,
                        MAKANAN_DEKRIPSI_MAKANAN,
                        MAKANAN_CARA_MEMASAK,
                        MAKANAN_HARGA,
                        MAKANAN_GAMBAR,
                        MAKANAN_ID_PROVINSI
                }, MAKANAN_ID_PROVINSI + " = \"" + id + "\"", null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan();

                makanan.setIdMakanan(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_MAKANAN)));
                makanan.setNamaMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_NAMA_MAKANAN)));
                makanan.setDekripsiMakanan(alldata.getString(alldata.getColumnIndex(MAKANAN_DEKRIPSI_MAKANAN)));
                makanan.setCaraMemasak(alldata.getString(alldata.getColumnIndex(MAKANAN_CARA_MEMASAK)));
                makanan.setHarga(alldata.getInt(alldata.getColumnIndex(MAKANAN_HARGA)));
                makanan.setGambar(alldata.getString(alldata.getColumnIndex(MAKANAN_GAMBAR)));

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(alldata.getInt(alldata.getColumnIndex(MAKANAN_ID_PROVINSI)));
                makanan.setProvinsi(provinsi);

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }


}
