package com.nivestudio.pinjamambulan.datamodel;

public class DataModelAmbulan {
    private String alamat,gambar,namamobil,nohp;

    public DataModelAmbulan(String alamat, String gambar, String namamobil, String nohp){
        this.alamat = alamat;
        this.gambar = gambar;
        this.namamobil = namamobil;
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNamamobil() {
        return namamobil;
    }

    public void setNamamobil(String namamobil) {
        this.namamobil = namamobil;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

}
