package com.sebasku.networks.apimodel;

/**
 * Created by fadil on 4/2/18.
 */

public class RequestCutiForm {
    private String email;
    private String nama;
    private String awal_cuti;
    private String akhir_cuti;
    private String keterangan;
    private String respons;
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAwal_cuti() {
        return awal_cuti;
    }

    public void setAwal_cuti(String awal_cuti) {
        this.awal_cuti = awal_cuti;
    }

    public String getAkhir_cuti() {
        return akhir_cuti;
    }

    public void setAkhir_cuti(String akhir_cuti) {
        this.akhir_cuti = akhir_cuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getRespons() {
        return respons;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestCutiForm(String email, String nama, String awal_cuti, String akhir_cuti, String keterangan, String respons, String status) {
        this.email = email;
        this.nama = nama;
        this.awal_cuti = awal_cuti;
        this.akhir_cuti = akhir_cuti;
        this.keterangan = keterangan;
        this.respons = respons;
        this.status = status;


    }
}
