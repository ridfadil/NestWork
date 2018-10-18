package com.sebasku.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.session.SessionManager;

public class MenuActivity extends AppCompatActivity {

    ImageView userFoto,slipGaji,Cuti,infoPerusahaan,presensiHarian;
    TextView job,nama;
    SessionManager session;
    String mNama,mPosisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new SessionManager(getApplicationContext());
        initializedObject();
        actionClicked();
        mNama = session.getNama();
        mPosisi = session.getPosisi();
        job.setText(mPosisi);
        nama.setText(mNama);
    }

    public void initializedObject(){
        userFoto = findViewById(R.id.user_foto);
        slipGaji = findViewById(R.id.slip_gaji);
        Cuti = findViewById(R.id.ajukan_cuti);
        infoPerusahaan = findViewById(R.id.informasi_perusahaan);
        presensiHarian = findViewById(R.id.presensi_harian);
        job = findViewById(R.id.job_role);
        nama = findViewById(R.id.nama_user);

    }

    public void actionClicked(){
        userFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        slipGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,SlipGajiActivity.class);
                startActivity(i);
            }
        });

        infoPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,ProfilPerusahaanActivity.class);
                startActivity(i);
            }
        });

        presensiHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,PresensiActivity.class);
                startActivity(i);
            }
        });

        Cuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,CutiActivity.class);
                startActivity(i);
            }
        });
    }

    public void logoutUser(){
        session = new SessionManager(getApplicationContext());
        session.logoutUser();
        String token = session.getAccesToken();
        Toast.makeText(MenuActivity.this, token, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
