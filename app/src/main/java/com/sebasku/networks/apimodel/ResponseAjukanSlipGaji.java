package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAjukanSlipGaji {


/*        "id": "5afef61b432d441465a048ad",
                "email": "faridpadilahhhh@gmail.com",
                "waktu": "1996-08-01T17:00:00.000Z",
                "status": "0",
                "gaji": 0,
                "jumlahTask": 0*/

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gaji")
    @Expose
    private String gaji;
    @SerializedName("jumlahTask")
    @Expose
    private String jumlahTask;

    public ResponseAjukanSlipGaji(String email, String waktu, String status, String gaji, String jumlahTask) {
        this.email = email;
        this.waktu = waktu;
        this.status = status;
        this.gaji = gaji;
        this.jumlahTask = jumlahTask;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getJumlahTask() {
        return jumlahTask;
    }

    public void setJumlahTask(String jumlahTask) {
        this.jumlahTask = jumlahTask;
    }
}
