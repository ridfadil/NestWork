package com.sebasku.networks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.ResponseCompanyProfile;
import com.sebasku.networks.apimodel.ResponseRiwayatCuti;
import com.sebasku.networks.session.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPerusahaanActivity extends AppCompatActivity {

    TextView email,nama,peraturan,profil;
    SessionManager session;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil");
        session = new SessionManager(getApplicationContext());
        token = session.getAccesToken();
        init();

        Call<List<ResponseCompanyProfile>> call = UtilsApi.getAPIService().getCompanyProfile("Bearer "+token);
        call.enqueue(new Callback<List<ResponseCompanyProfile>>() {
            @Override
            public void onResponse(Call<List<ResponseCompanyProfile>> call, Response<List<ResponseCompanyProfile>> response) {
                if (response.code()==201){
                        List<ResponseCompanyProfile> responsCompany = response.body();
                        String email = responsCompany.get(0).getEmail();
                        String profil = responsCompany.get(0).getProfil();
                        String nama = responsCompany.get(0).getNamaPerusahaan();
                        String peraturan = responsCompany.get(0).getPeraturan();
                        setView(email,profil,nama,peraturan);
                }
                else {
                    Toast.makeText(getApplicationContext(), "not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseCompanyProfile>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Tombol Aksi Kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(){
        email = findViewById(R.id.tv_email_company);
        nama = findViewById(R.id.tv_nama_company);
        peraturan = findViewById(R.id.tv_peraturan_company);
        profil = findViewById(R.id.tv_profil_company);
    }

    public void setView(String mEmail,String mProfil,String mNama,String mPeraturan){
        email.setText(mEmail);
        nama.setText(mNama);
        peraturan.setText(mPeraturan);
        profil.setText(mProfil);
    }
}