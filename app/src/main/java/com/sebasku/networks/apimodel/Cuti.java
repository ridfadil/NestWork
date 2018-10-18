package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fadil on 4/2/18.
 */


/*        "id": "5ac20dc3e5938d0f9cdcde76",
                "email": "farid1@gmail.com",
                "awal_cuti": "1996-01-01T17:00:00.000Z",
                "akhir_cuti": "2019-09-08T17:00:00.000Z",
                "keterangan": "umroh",
                "status": "waiting to approve"*/
public class Cuti {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("awal_cuti")
    @Expose
    private String awal_cuti;
    @SerializedName("akhir_cuti")
    @Expose
    private String akhir_cuti;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("respons")
    @Expose
    private String respons;

    public Cuti(String id, String email, String awal_cuti, String akhir_cuti, String keterangan, String status, String respons) {
        this.id = id;
        this.email = email;
        this.awal_cuti = awal_cuti;
        this.akhir_cuti = akhir_cuti;
        this.keterangan = keterangan;
        this.status = status;
        this.respons = respons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespons() {
        return respons;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }
}
