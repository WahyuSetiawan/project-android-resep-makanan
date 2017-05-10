package com.resepmakanantradisional.model;

public class Makanan {
    private int idMakanan;
    private String namaMakanan;
    private String dekripsiMakanan;
    private String caraMemasak;
    private int harga;
    private String gambar;
    private Provinsi provinsi;

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    public int getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(int idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getDekripsiMakanan() {
        return dekripsiMakanan;
    }

    public void setDekripsiMakanan(String dekripsiMakanan) {
        this.dekripsiMakanan = dekripsiMakanan;
    }

    public String getCaraMemasak() {
        return caraMemasak;
    }

    public void setCaraMemasak(String caraMemasak) {
        this.caraMemasak = caraMemasak;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
