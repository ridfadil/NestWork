package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUpdateProfil {
    /*{
        "id": "5af2c3795d49690fc9540a2f",
            "email": "tsani@gmail.com",
            "nama": "Tania Tsani",
            "posisi": "Android Developer",
            "noHp": "089567567",
        "role": "user",
            "avatarLink": "http://localhost:3000/2018-05-10T03:15:46.525ZScreenshot from 2018-04-26 07-45-07.png",
            "createdAt": "2018-05-09T09:46:34.008Z"
    }*/
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("posisi")
    @Expose
    private String posisi;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("avatarLink")
    @Expose
    private String avatarLink;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public ResponseUpdateProfil(String id, String email, String nama, String posisi, String role, String avatarLink, String createdAt) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.posisi = posisi;
        this.role = role;
        this.avatarLink = avatarLink;
        this.createdAt = createdAt;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
