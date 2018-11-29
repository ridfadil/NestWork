package com.sebasku.networks.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.ProfileUpdateForm;
import com.sebasku.networks.apimodel.ResponseUpdateProfil;
import com.sebasku.networks.session.SessionManager;

import retrofit2.Call;

import java.util.Calendar;

import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Calendar myCalendar;
    SessionManager session;
    EditText nama, email, noHp, bidang, tanggal;
    String mNama, mEmail, mNoHp, mBidang, mTanggal;
    String getNama, getEmail, getNoHp, getBidang;
    Button submit, changePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");
        session = new SessionManager(getApplicationContext());
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        init();
        setProfil();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBidang = bidang.getText().toString();
                mEmail = email.getText().toString();
                mNama = nama.getText().toString();
                mNoHp = noHp.getText().toString();
                // mTanggal = tanggal.getText().toString();
                mTanggal = "01-01-2018";
                updateProfil(mNama, mEmail, mBidang, mNoHp, mTanggal);
            }
        });

        /*tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfilActivity.this, EditProfilActivity.this, year,month,day);
                datePickerDialog.show();
            }
        });*/

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfilActivity.this, EditPassword.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateProfil(String mNama, String mEmail, String mBidang, String mNoHp, String mTanggal) {
        ProfileUpdateForm update = new ProfileUpdateForm(mNama, mEmail, mBidang, mNoHp, mTanggal);
        // User responsesId = response.body().getUser();
        session = new SessionManager(getApplicationContext());
        String token = session.getAccesToken();
        String id = session.getId();
        String b = "Bearer ";
        String tokenize = b + token;
        Call<ResponseUpdateProfil> call = UtilsApi.getAPIService().updateProfil(tokenize, id, update);

        call.enqueue(new Callback<ResponseUpdateProfil>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfil> call, Response<ResponseUpdateProfil> response) {
                if (response.isSuccessful()) {
                    ResponseUpdateProfil responses = response.body();
                    String posisi = responses.getPosisi();
                    String nama = responses.getNama();
                    String email = responses.getEmail();

                    session.createPosisiSession(posisi);
                    session.createNamaSession(nama);
                    session.createEmailSession(email);

                    Toast.makeText(getApplicationContext(), "Sukses Edit Profil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditProfilActivity.this, MenuActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Silakan Login kembali", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditProfilActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        bidang = findViewById(R.id.et_bidang_update_profil);
        nama = findViewById(R.id.et_nama_update_profil);
        email = findViewById(R.id.et_email_update_profil);
        noHp = findViewById(R.id.et_nohp_update_profil);
      //  tanggal = findViewById(R.id.et_tanggal_update_profil);
        submit = findViewById(R.id.btn_submit_edit_profil);
        changePassword = findViewById(R.id.btn_stelan_privasi_akun);
    }

    public void setProfil() {
   /*     getBidang = session.getPosisi();
        getEmail = session.getEmail();
        getNama = session.getNama();
        getNoHp = session.getNoHp();*/

        getNama = getIntent().getStringExtra("nama");
        getEmail = getIntent().getStringExtra("email");
        getBidang = getIntent().getStringExtra("posisi");
        getNoHp = getIntent().getStringExtra("no");

        bidang.setText(getBidang);
        email.setText(getEmail);
        nama.setText(getNama);
        noHp.setText(getNoHp);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int yearFinal = i;
        int monthFinal = i1 + 1;
        int dayFinal = i2;
        tanggal.setText("" + dayFinal + "-" + monthFinal + "-" + yearFinal);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
