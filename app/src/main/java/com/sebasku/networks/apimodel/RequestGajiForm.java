package com.sebasku.networks.apimodel;

import com.google.gson.annotations.SerializedName;

public class RequestGajiForm {
/*    	"email":"tsani@gmail.com",
                "waktu":"08-02-1996",
                "status":"0",
                "gaji":"0"*/

    private String email;
    private String waktu;
    private String status;
    private String gaji;

    public RequestGajiForm(String email, String waktu, String status, String gaji) {
        this.email = email;
        this.waktu = waktu;
        this.status = status;
        this.gaji = gaji;
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
}
