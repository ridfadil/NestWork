package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCompanyProfile {

            /*"_id": "5af2c54b5d49690fc9540a33",
             "nama_perusahaan": "NestWork",
             "email": "nestwork.com",
             "profil": "Dalam pertemuan tanpa sengaja tadi, dua pendiri Google tersebut sering terlibat diskusi panjang. Keduanya memiliki pendapat dan pandangan yang berbeda sehingga sering terlibat perdebatan",
             "peraturan": "Dalam pertemuan tanpa sengaja tadi, dua pendiri Google tersebut sering terlibat diskusi panjang. Keduanya memiliki pendapat dan pandangan yang berbeda sehingga sering terlibat perdebatan. Namun, perbedaan pemikiran mereka justru menghasilkan sebuah pendekatan unik dalam menyelesaikan salah satu tantangan terbesar pada dunia komputer. Yakni, masalah bagaimana memperoleh kembali data dari set data masif",
             "createdAt": "2018-05-09T09:54:19.271Z",
             "updatedAt": "2018-05-09T09:54:19.271Z",
             "__v": 0*/


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nama_perusahaan")
    @Expose
    private String namaPerusahaan;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profil")
    @Expose
    private String profil;
    @SerializedName("peraturan")
    @Expose
    private String peraturan;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private String __v;

    public ResponseCompanyProfile(String id, String namaPerusahaan, String email, String profil, String peraturan, String createdAt, String updatedAt, String __v) {
        this.id = id;
        this.namaPerusahaan = namaPerusahaan;
        this.email = email;
        this.profil = profil;
        this.peraturan = peraturan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getPeraturan() {
        return peraturan;
    }

    public void setPeraturan(String peraturan) {
        this.peraturan = peraturan;
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

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
