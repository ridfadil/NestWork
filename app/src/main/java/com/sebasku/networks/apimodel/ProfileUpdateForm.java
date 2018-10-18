package com.sebasku.networks.apimodel;

/**
 * Created by fadil on 4/1/18.
 */

public class ProfileUpdateForm {

    private String nama;
    private String email;
    private String posisi;
    private String noHp;
    private String tanggalLahir;

    public ProfileUpdateForm(String nama, String email, String posisi, String noHp, String tanggalLahir) {
        this.nama = nama;
        this.email = email;
        this.posisi = posisi;
        this.noHp = noHp;
        this.tanggalLahir = tanggalLahir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}

