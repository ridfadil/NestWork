package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRiwayatGaji {

/*     "status": "0",
             "jumlahTask": 0,
             "_id": "5af2e07ceb5c971fe04b283b",
             "email": "tsani@gmail.com",
             "gaji": 0,
             "waktu": "1996-02-01T17:00:00.000Z",
             "createdAt": "2018-05-09T11:50:20.170Z",
             "updatedAt": "2018-05-09T11:50:20.170Z",
             "__v": 0*/

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jumlahTask")
    @Expose
    private String jumlahTask;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("gaji")
    @Expose
    private String gaji;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private int v;

    public ResponseRiwayatGaji(String status, String jumlahTask, String id, String email, String waktu, String gaji, String createdAt, String updatedAt, int v) {
        this.status = status;
        this.jumlahTask = jumlahTask;
        this.id = id;
        this.email = email;
        this.waktu = waktu;
        this.gaji = gaji;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJumlahTask() {
        return jumlahTask;
    }

    public void setJumlahTask(String jumlahTask) {
        this.jumlahTask = jumlahTask;
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

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
