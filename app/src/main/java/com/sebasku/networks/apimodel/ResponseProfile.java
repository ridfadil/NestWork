package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fadil on 4/1/18.
 */

/*    "id": "5af2c3795d49690fc9540a2f",
            "email": "tsani@gmail.com",
            "nama": "fadli",
            "posisi": "Android Developer",
            "noHp": "0897867434",
            "picture": {},
            "role": "user",
            "createdAt": "2018-05-09T09:46:34.008Z"*/

public class ResponseProfile {

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
        @SerializedName("noHp")
        @Expose
        private String noHp;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;

    public ResponseProfile(String id, String email, String nama, String posisi, String noHp, String role, String createdAt) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.posisi = posisi;
        this.noHp = noHp;
        this.role = role;
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

        public String getNoHp() {
            return noHp;
        }

        public void setNoHp(String noHp) {
            this.noHp = noHp;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
}