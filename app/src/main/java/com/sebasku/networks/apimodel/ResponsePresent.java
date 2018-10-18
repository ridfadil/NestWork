package com.sebasku.networks.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePresent {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("backlog")
    @Expose
    private String backlog;
    @SerializedName("status_prs")
    @Expose
    private String status_prs;
    @SerializedName("task")
    @Expose
    private String task;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public ResponsePresent(String id, String email, String nama, String backlog, String status_prs, String task, String note, String createdAt) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.backlog = backlog;
        this.status_prs = status_prs;
        this.task = task;
        this.note = note;
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

    public String getBacklog() {
        return backlog;
    }

    public void setBacklog(String backlog) {
        this.backlog = backlog;
    }

    public String getStatus_prs() {
        return status_prs;
    }

    public void setStatus_prs(String status_prs) {
        this.status_prs = status_prs;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
