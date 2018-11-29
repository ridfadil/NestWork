package com.sebasku.networks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.CekLoginForm;
import com.sebasku.networks.apimodel.ChangePasswordForm;
import com.sebasku.networks.apimodel.LoginForm;
import com.sebasku.networks.apimodel.ProfileUpdateForm;
import com.sebasku.networks.apimodel.ResponseLogin;
import com.sebasku.networks.apimodel.ResponseUpdateProfil;
import com.sebasku.networks.apimodel.Token;
import com.sebasku.networks.apimodel.User;
import com.sebasku.networks.session.SessionManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPassword extends AppCompatActivity {
    EditText usernameLama, usernameBaru, passLama, passBaru, rePass;
    String mUsernameLama, mUsernameBaru, mPassLama, mPassBaru, mRePass;
    Button submit;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Akun");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        init();
        actionClick();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void gantiPass(String usernameBaruPost, String passBaruPost) {
        ChangePasswordForm update = new ChangePasswordForm(usernameBaruPost, passBaruPost);
        // User responsesId = response.body().getUser();
        session = new SessionManager(getApplicationContext());
        String token = session.getAccesToken();
        String id = session.getId();
        String b = "Bearer ";
        String tokenize = b + token;
        Call<ResponseUpdateProfil> call = UtilsApi.getAPIService().updatePass(tokenize, id, update);

        call.enqueue(new Callback<ResponseUpdateProfil>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfil> call, Response<ResponseUpdateProfil> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sukses Mengganti Password", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Silakan Login Kembali", Toast.LENGTH_SHORT).show();
                    session.logoutUser();
                    Intent i = new Intent(EditPassword.this, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Silakan Login kembali", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditPassword.this, LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfil> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekLogin(String mUsernameLama, String mPassLama, String mUsernameBaru, String mPassBaru, String mRePass) {

        CekLoginForm login = new CekLoginForm(mUsernameLama, mPassLama);
        Call<ResponseLogin> call = UtilsApi.getAPIService().cekLogin(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    final String usernameBaruPost, passBaruPost;
                    session = new SessionManager(getApplicationContext());
                    User responsesId = response.body().getUser();
                    User responsesNama = response.body().getUser();
                    User responsesPosisi = response.body().getUser();
                    User responsesNoHp = response.body().getUser();
                    Token responsesToken = response.body().getToken();
                    String token = responsesToken.getAccessToken();
                    String id = responsesId.getId();
                    String email = responsesId.getEmail();
                    String noHp = responsesNoHp.getNoHp();
                    String nama = responsesNama.getNama();
                    String posisi = responsesPosisi.getPosisi();
                    session.createPosisiSession(posisi);
                    session.createIdSession(id);
                    session.createEmailSession(email);
                    session.createLoginSession(token);
                    session.createNamaSession(nama);
                    session.createNoHpSession(noHp);
                    usernameBaruPost = usernameBaru.getText().toString();
                    passBaruPost = passBaru.getText().toString();
                    gantiPass(usernameBaruPost, passBaruPost);
                } else {
                    Toast.makeText(EditPassword.this, "Password dan Email Lama Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actionClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsernameBaru = usernameBaru.getText().toString();
                mUsernameLama = usernameLama.getText().toString();
                mPassBaru = passBaru.getText().toString();
                mPassLama = passLama.getText().toString();
                mRePass = rePass.getText().toString();
                if (mUsernameLama != null && mUsernameBaru != null && mPassLama != null && mPassBaru != null && mRePass != null) {
                    if (mPassBaru.equals(mRePass)) {
                        if (mPassBaru.length() > 6) {
                            cekLogin(mUsernameLama, mPassLama, mUsernameBaru, mPassBaru, mRePass);
                        } else {
                            Toast.makeText(EditPassword.this, "Maaf Password minimaml 6 Karakter", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditPassword.this, "Retype Password tidak sesuai", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditPassword.this, "Form Masih ada yang kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init() {
        usernameBaru = findViewById(R.id.et_newusername_change_password);
        usernameLama = findViewById(R.id.et_username_change_password);
        passBaru = findViewById(R.id.et_newpassword_change_password);
        passLama = findViewById(R.id.et_password_change_password);
        rePass = findViewById(R.id.et_Retype_change_password);
        submit = findViewById(R.id.btn_submit_edit_password);
    }
}
