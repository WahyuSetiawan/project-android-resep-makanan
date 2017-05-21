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
    static final int DATABASE_VERSION = 37;
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
        provinsi.setNamaProvinsi("Yogyakarta");
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

        provinsi = new Provinsi();
        mval = new ContentValues();

        provinsi.setIdProvinsi(3);
        provinsi.setNamaProvinsi("Palembang");
        provinsi.setLatitude((float) -2.91673);
        provinsi.setLongitude((float) 104.7458);

        mval.put(PROVINSI_NAMA_PROVINSI, provinsi.getNamaProvinsi());
        mval.put(PROVINSI_LATITUDE, provinsi.getLatitude());
        mval.put(PROVINSI_LONGITUDE, provinsi.getLongitude());

        db.insert(TABLE_PROVINSI, null, mval);

        provinsi = new Provinsi();
        mval = new ContentValues();

        provinsi.setIdProvinsi(4);
        provinsi.setNamaProvinsi("Jakarta");
        provinsi.setLatitude((float) -6.21462);
        provinsi.setLongitude((float) 106.84513);

        mval.put(PROVINSI_NAMA_PROVINSI, provinsi.getNamaProvinsi());
        mval.put(PROVINSI_LATITUDE, provinsi.getLatitude());
        mval.put(PROVINSI_LONGITUDE, provinsi.getLongitude());

        db.insert(TABLE_PROVINSI, null, mval);

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_nasi_gudeg)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();

                makanan.setIdMakanan(1);
                makanan.setNamaMakanan("Gudeg");
                makanan.setCaraMemasak("Bahan-bahan yang digunakan:\n" +
                        "-\t200 gram gula aren (sisir)\n" +
                        "-\t10 butir telur (rebus matang lalu kupas)\n" +
                        "-\t10 lembar daun salam\n" +
                        "-\t4 cm lengkuas (iris memanjang)\n" +
                        "-\t2 liter santan\n" +
                        "-\t1 liter air kelapa\n" +
                        "-\t1 kg nangka muda (kupas lalu potong-potong)\n" +
                        "Bumbu-bumbu yang dihaluskan:\n" +
                        "-\t15 butir bawang merah\n" +
                        "-\t10 siung bawah putih\n" +
                        "-\t10 butir kemiri (sangrai)\n" +
                        "-\t1 sendok the ketumbar (sangrai)\n" +
                        "-\t2 sendok makan garam\n" +
                        "-\t½ sendok teh merica bubuk\n" +
                        "Pelengkap:\n" +
                        "-\tKuah areh, opor ayam dan tahu, sambal goreng krecek, nasi putih, sambal\n" +
                        "Cara membuat:\n" +
                        "-\tCuci nangka muda hingga bersih lalu rebus sebentar kemudia diangkat\n" +
                        "-\tAlasi dasar panci dengan daun salam, letakkan irisan lengkuas diatas daun salam. Masukkan potongan nangka muda, telur rebus, dan gula aren\n" +
                        "-\tCampurkan setengah bagian air kelapa dengan bumbu yang telah dihaluskan, aduk hingga tercampur rata kemudian tuangkan kedalam panci\n" +
                        "-\tTuangkan sisa air kelapa sampai nangka dan telur terendam lalu tutup panci\n" +
                        "-\tMasak dengan menggunakan api sedang selama 2 jam (jangan dibuka tutup panci). Jika airnya sudah sedikit, angkat dan sisihkan telurnya.\n" +
                        "-\tTuangkan santan, aduk-aduk sambil hancurkan potongan nangka. Masukkan kembali telur sampai sedikit terkubur dalam nangka\n" +
                        "-\tKecilkan api, masak kembali selama kurang lebih 3-4 jam sambil diaduk sesekali. Masak hingga matang atau santan habis dan gudeg berwarna coklat kemerahan\n" +
                        "-\tSiramkan areh/kuah opor ayam kental diatas gudeg nangka\n" +
                        "-\tSajikan bersama opor ayam dan tahu, sambel goreng krecek, dan nasi putih. Lengkapi dengan sambal jika suka\n");
                makanan.setDekripsiMakanan("Belum diketahui jelas tentang sejarah Gudeg. Beberapa pandangan mengkaitkan Gudeg sebagai makanan dari Kraton Yogyakarta, sementara lainnya berpandangan bahwa Gudeg telah lama ada sejak penyerbuan pertama ke Batavia pada 1726-1728 oleh pasukan sultan agung yang tercatat dalam sejarah meski belum dapat dibuktikan kebenarannya. Namun dalam berbagai kesimpulan mengenai sejarah Gudeg dapat disimpulkan bahwa Gudeg adalah makanan masyarakat jaman dulu karena bahan bakunya yaitu nangka muda yang mudah untuk ditemukan di pekarangan sekitar rumah warga. Nangka tersebut kemudian diolah dan dikembangkan sehingga menjadi Gudeg makanan khas masyarakat Yogyakarta sampai saat ini.");
                makanan.setGambar(s);
                makanan.setHarga(26000);

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

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_kue_pia_kacang_ijo)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(2);
                makanan.setNamaMakanan("Bakpia");
                makanan.setCaraMemasak("Bahan kulit bakpia:\n" +
                        "-\t125 gram tepung terigu rendah protein\n" +
                        "-\t65 gram tepung terigu tinggi protein\n" +
                        "-\t100 ml air\n" +
                        "-\t2 sendok makan gula pasir\n" +
                        "-\t½ sendok makan garam\n" +
                        "-\t200 ml minyak sayur\n" +
                        "-\t(untuk campuran adonan 50 ml dan untuk rendaman 150 ml)\n" +
                        "Bahan lapisan bakpia:\n" +
                        "-\t65 gram tepung terigu rendah protein\n" +
                        "-\t25 ml minyak sayur\n" +
                        "-\t½ sendok makan margarine\n" +
                        "Bahan isian bakpia:\n" +
                        "-\t100 gram kacang ijo/kacang hijau yang telah dikupas (rendam semalaman)\n" +
                        "-\t150 ml santan (dari ½ butir kelapa)\n" +
                        "-\t1/8 sendok teh garam\n" +
                        "-\t1 lembar daun pandan\n" +
                        "-\t25 gram gula pasir\n" +
                        "-\t50 gram gula merah atau gula jawa\n" +
                        "-\t1 sendok makan minyak\n" +
                        "Cara membuat:\n" +
                        "-\tMembuat Bahan Isian Bakpia : Kukus kacang ijo yang sudah ditiriskan, selama kurang lebih 20 menit sampai kacang mengembang, lalu angkat\n" +
                        "-\tMasukan gula pasir, gula merah, garam, santan dan daun pandan, masak hingga mengental rata (kalis), tambahkan minyak sebelum api dimatikan. Aduk campuarn tersebut hingga mengental rata (kalis) dan licin. Angkat lalu tunggu sampai agak dingin lalu bentukla menjadi bola-bola kecil\n" +
                        "-\tMembuat Bahan Lapisan Bakpia : aduk semua bahan lapisan hingga rata, lalu sisihkan\n" +
                        "-\tMembuat Bahan Kulit Bakpia : panaskan air tidak sampai mendidih, masukan gula aduk sampai gula larut semua dan merata lalu angkat. campurkan terigu dan garam hingga rata, tuangakan air larutan gula sedikit demi sedikit sambil diuleni hingga merata\n" +
                        "-\tTuangkan minyak sayur sambil diuleni hingga merata dan kalis\n" +
                        "-\tAmbil adonan kira-kira 10 gram. Pipihkan adonan, lalu ambil sedikit adonan lapisan, ratakan diatas permukaan adonan sebelumnya hingga rata\n" +
                        "-\tLipat adonan dan rekatkan ujungnya membentuk bulatan\n" +
                        "-\tRendam adonan bulat ke dalam minyak yang tersisa selama kira-kira 15 menit\n" +
                        "-\tPipihkan adonan sampai agak tipis, lalu isi dengan adonan bahan isian, bentuk bulat pipih\n" +
                        "-\tPanggang adonan yang sudah diisi kedalam oven dengan temperatur 200 derajat C smapai matang kira-kira 15-20 menit. Agar warna kuning merata di bagian atas bakia anda bisa membolak-balik adonan. Sekiranya sudah sesuai keinginan angkat\n");
                makanan.setDekripsiMakanan("Pada tahun 1948, ada keluarga keturunan Tionghoa bernama Goei Gee Oe yang mencoba membuat Bakpia sebagai industri rumahan. Saat itu Bakpia buatannya tidak dijual di toko melainkan dijajakan secara eceran, dari rumah ke rumah. Bakpia buatan Goei Gee Oe itu juga belum dikemas dan diberi label seperti saat ini, melainkan hanya dimasukkan dalam besek (wadah makanan berbentuk kotak yang terbuat dari anyaman bambu)");
                makanan.setGambar(s);
                makanan.setHarga(22000);

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

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_pempek)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(4);
                makanan.setNamaMakanan("Pempek");
                makanan.setCaraMemasak("Bahan-bahan yang digunakan:\n" +
                        "-\tIkan tenggiri segar 500 gram\n" +
                        "-\tTepung sagu tani 500 gram\n" +
                        "-\tTelur 2 butir\n" +
                        "-\tGaram dapur satu ½ sendok the\n" +
                        "-\tAir bersih 250 gram (air es)\n" +
                        "-\tPenyedap rasa atau vetsin 1 sendok the\n" +
                        "Bahan untuk membuat cuka:\n" +
                        "-\t½ kg gula aren\n" +
                        "-\tAsam jawa 50 gram, peras dengan menggunakan sedikit air\n" +
                        "-\tCuka putih 5 sendok the\n" +
                        "-\tAir bersih 5 gelas\n" +
                        "Bumbu cuka ( Haluskan ):\n" +
                        "-\tBawang putih 5 siung\n" +
                        "-\tEbi dua sendok makan\n" +
                        "-\tCabe rawit 10 buah ( selera masing-masing)\n" +
                        "-\tTongcai satu sendok makan berikut tangkainya\n" +
                        "-\tGaram 1 sendok makan ( disesuaikan )\n" +
                        "Cara membuat kuah cuka:\n" +
                        "-\tDidihkan air bersama dengan gula merah, asam jawa, air, dan cuka menggunakan api kecil. Dan setelah gula larut, angkat kemudian saring\n" +
                        "-\tMasukkan bumbu cuka yang sudah dihaluskan, kemudian masukkan cabe, garam sebaiknya sambil di cicipi dan disesuaikan dengan selera\n" +
                        "-\tSetelah mendidih dan rasanta enak, angkat kemudian saring\n" +
                        "Cara membuat pempek:\n" +
                        "-\tLangkah pertama untuk membuat pempek adalah membuat adonan pempek sebelum membuat cuka, siapkan semua bahan yang telah di sebutkan di atas\n" +
                        "-\tHaluskan daging tenggiri, setelah halus campurkan dengan telur, garam, dan vetsin. Aduk-aduk hingga rata\n" +
                        "-\tKemudian tuang air dingin sedikit demi sedikit hingga tercampur semua, sambil di aduk hingga merata\n" +
                        "-\tLalu tambahkan tepung sagu sambil diuleni hingga rata dan kalis\n" +
                        "-\tSetelah kalis, bentuk adonan pempek sesuai jenis pempek yang anda inginkan\n" +
                        "-\tSetelah adonan sudah terbentuk. Kemudian rebus dengan menggunakan air yang banyak hingga mengapung dan tiriskan\n" +
                        "-\tLalu goreng pempek dahulu sebelum dihidangkan dengan kuah cuka\n");
                makanan.setDekripsiMakanan("Berdasarkan cerita rakyat, sekitar tahun 1617 seorang apek berusia 65 tahun yang tinggal di daerah perakitan (tepian sungai musi) merasa prihatin menyaksikan tangkapan ikan yang berlimpah di sungai musi yang belum seluruhnya dimanfaatkan dengan baik, hanya sebatas digoren dan dipindang. Ia kemudian mencoba alternative pengolahan lain. Ia mencampur daging ikan giling dengan tepung tapioca, sehingga dihasilkan makanan baru. Makanan baru tersebut dijajakan oleh para apek dengan bersepeda keliling kota. Oleh karena penjualnya dipanggil dengan sebutan “pek…apek”, maka makanan tersebut akhirnya dikenal sebagai empek-empek atau pempek.");
                makanan.setGambar(s);
                makanan.setHarga(2500);

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(3);
                makanan.setProvinsi(provinsi);

                ContentValues mval = contentValuesMakanan(makanan);

                // db.insert(TABLE_MAKANAN, null, mval);
                insertMakanan(makanan);
            }

            @Override
            public void onFailure() {

            }
        });

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_kerak_telor_betawi)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(5);
                makanan.setNamaMakanan("Kerak Telor");
                makanan.setCaraMemasak("Bahan yang digunakan:\n" +
                        "-\t100 gram beras ketan putih\n" +
                        "-\t250 ml air\n" +
                        "-\t100 gram kelapa parut, sangrai untuk penabur\n" +
                        "-\t15 gram ebi udang, seduh, sangrai, dan haluskan\n" +
                        "-\t5 butir telur bebek\n" +
                        "-\t30 gram bawang merah, goreng kering\n" +
                        "-\t1 sendok makan minyak goreng untuk menumis bumbu halus\n" +
                        "-\tBawang merah goreng khusus untuk taburan\n" +
                        "Bumbu yang dihaluskan:\n" +
                        "-\t4 buah cabai merah keriting\n" +
                        "-\t½ sendok the merica butiran\n" +
                        "-\t3 cm kencur\n" +
                        "-\t1 cm jahe\n" +
                        "Cara membuat:\n" +
                        "-\tRendam beras ketan putih di dalam air selama satu malam, tiriskan\n" +
                        "-\tPanaskan minyak, tumis bumbu halus hingga harum\n" +
                        "-\tBubuhkan 1 ½ sendok makan beras ketan putih pada wajan cekung yang sudah panas. Siram dengan 3 sendok makan air redaman beras, biarkan hingga agak kering\n" +
                        "-\tPada satu tempat, kocok 1 butir telur bebek, ½ sendok teh bumbu halus yang sudah ditumis, ½ sendok ebi, ½ sendok makan bawang merah goreng, 1/8 sendok the gula pasir, dan 1/8 sendok the garam bubuk\n" +
                        "-\tSiram campuran tersebut ke atas ketan pada wajan, aduk sambil ratakan dan atur ketebalannya dengan mengira-ngira. Tutup wajan hingga matang. Balik wajan cekung di atas bara api, biarkan sampai benar-benar matang\n" +
                        "-\tTerakhir, taburi kelapa sangrai dan bawang goreng sebelum disajikan\n");
                makanan.setDekripsiMakanan("Kerak telor merupakan warisan masa lalu. Menurut sejarah Kerak Telor sudah ada pada zaman Belanda menjajah Indonesia pada waktu dulu. Saat itu kota yang bernama Jakarta masih banyak di tumbuhi pohon kelapa. Karena dahulu hasil kelapa sangat melimpah yang membuat Jakarta masa lalu bernama sunda kelapa. Buah kelapa yang pada saat itu sangat melimpah sangat di manfaatkan oleh penduduk Jakarta untuk membuat aneka masakan. Seperti Nasi Uduk, Soto Betawi, Kerak Telor dan makanan khas Jakarta lainnya.");
                makanan.setGambar(s);
                makanan.setHarga(15000);

                Provinsi provinsi = new Provinsi();
                provinsi.setIdProvinsi(4);
                makanan.setProvinsi(provinsi);

                ContentValues mval = contentValuesMakanan(makanan);

                // db.insert(TABLE_MAKANAN, null, mval);
                insertMakanan(makanan);
            }

            @Override
            public void onFailure() {

            }
        });//*/

        Base64Image.with(context).encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_tahu_gimbal_semarang)).into(new RequestEncode.Encode() {
            @Override
            public void onSuccess(String s) {
                Makanan makanan = new Makanan();
                makanan.setIdMakanan(3);
                makanan.setNamaMakanan("Tahu Gimbal");
                makanan.setCaraMemasak("Bahan-bahan yang digunakan:\n" +
                        "-\t1 kotak tahu putih, potong-potong\n" +
                        "-\t150 gram kol/kubis, iris tipis\n" +
                        "Bahan udang:\n" +
                        "-\t150 gram udang kupas\n" +
                        "-\t100 gram tepung terigu\n" +
                        "-\t50 gram tepung beras\n" +
                        "-\t1 butir telur\n" +
                        "-\t½ sendok teh merica\n" +
                        "-\t1 sendok teh garam\n" +
                        "-\t2 batang daun bawang iris tipis\n" +
                        "-\t1 batang seledri iris tipis\n" +
                        "-\tAir secukupnya\n" +
                        "Bumbu kacang:\n" +
                        "-\t200 gram kacang tanah goreng\n" +
                        "-\t5 siung bawang putih, iris tipis dan goreng\n" +
                        "-\t2 sendok makan gula merah,sisir\n" +
                        "-\t5 sendok makan kecap manis\n" +
                        "-\tCabe rawit secukupnya/sesuai selera\n" +
                        "-\t100 ml air panas\n" +
                        "Cara membuat:\n" +
                        "-\tRendam tahu dalam air garam, biarkan kurang lebih 10 menit dan goreng hingga matang. Potong kecil-kecil dan sisihkan\n" +
                        "-\tCampur semua bahan untuk udang gimbal hingga jadi adonan kental. Goreng udang yang terbalut adonan hingga matang kecoklatan. Potong dan sisihkan\n" +
                        "-\tUntuk bumbu kacang, haluskan di cobek semua bahan dan tuang air panas sedikit demi sedikit dicampur dan aduk rata. Terakhir campurkan dengan kecap manis\n" +
                        "-\tTata tahu dan udang gimbal, kemudian taburi dengan kol secukupnya. Tuangkan dengan saus kacang\n");
                makanan.setDekripsiMakanan("Tahu gimbal adalah makanan tradisional yang terdiri dari tahu goreng, irisan kubis, lontong, taoge, telur dan gimbal yang di campur dengan bumbu kacang. Makanan satu ini merupakan salah satu makanan tradisional yang sangat terkenal di Indonesia. Khususnya di Semarang, Jawa tengah, yang menjadikan tahu gimbal sebagai salah satu makanan khasnya.");
                makanan.setGambar(s);
                makanan.setHarga(12000);

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
