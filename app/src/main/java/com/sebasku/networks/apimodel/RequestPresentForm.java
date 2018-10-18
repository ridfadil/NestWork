package com.sebasku.networks.apimodel;

public class RequestPresentForm {

    private String email;
    private String nama;
    private String backlog;
    private String status_prs;
    private String task;
    private String note;

    public RequestPresentForm(String email, String nama, String backlog, String status_prs, String task, String note) {
        this.email = email;
        this.nama = nama;
        this.backlog = backlog;
        this.status_prs = status_prs;
        this.task = task;
        this.note = note;
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
}
