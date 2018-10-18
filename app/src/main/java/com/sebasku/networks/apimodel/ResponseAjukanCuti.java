package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fadil on 4/2/18.
 */

public class ResponseAjukanCuti {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cutiTransformed")
    @Expose
    private Cuti ResCuti;

    public ResponseAjukanCuti(String message, String status, Cuti resCuti) {
        this.message = message;
        this.status = status;
        ResCuti = resCuti;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cuti getResCuti() {
        return ResCuti;
    }

    public void setResCuti(Cuti resCuti) {
        ResCuti = resCuti;
    }
}
