package com.sebasku.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.sebasku.networks.R;

public class DetailGajiActivity extends AppCompatActivity {

    TextView sesi,gaji,posisi,jumTask,nama,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gaji);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Gaji");
        init();
        String mSesi = intent.getStringExtra("sesi");
        String mNama = intent.getStringExtra("nama");
        String mEmail = intent.getStringExtra("email");
        String mPosisi = intent.getStringExtra("posisi");
        String mTask = intent.getStringExtra("task");
        String mGaji = intent.getStringExtra("gaji");

        sesi.setText(mSesi);
        nama.setText(mNama);
        posisi.setText(mPosisi);
        jumTask.setText(mTask);
        gaji.setText(mGaji);
        email.setText(mEmail);

    }


    //Tombol Aksi Kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public  void init(){
        sesi = findViewById(R.id.tv_sesi);
        nama = findViewById(R.id.tv_nama);
        posisi = findViewById(R.id.tv_posisi);
        jumTask = findViewById(R.id.tv_jum_task);
        gaji = findViewById(R.id.tv_jum_gaji);
        email = findViewById(R.id.tv_email_detail_gaji);

    }
}
