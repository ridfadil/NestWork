package com.sebasku.networks.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.Profile;
import com.sebasku.networks.apimodel.ResponseProfile;
import com.sebasku.networks.apimodel.ResponseUploadAvatar;
import com.sebasku.networks.session.SessionManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Button edit;
    ImageView upload;
    TextView mNama, mPosisi, mEmail, mHp;
    SessionManager session;
    String token;
    String id;
    String imagePath;
    String nama="",posisi = "",email="",no="";
    public static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        initialized();
        getProfile();
        onClickAction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initialized() {
        edit = findViewById(R.id.btn_edit);
        mNama = findViewById(R.id.tv_nama1);
        mHp = findViewById(R.id.tv_no_hp1);
        mPosisi = findViewById(R.id.tv_bidang_developer1);
        mEmail = findViewById(R.id.tv_email1);
       // upload = findViewById(R.id.btn_upload_foto);

    }

    public void onClickAction() {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, EditProfilActivity.class);
                i.putExtra("nama",nama);
                i.putExtra("posisi",posisi);
                i.putExtra("email",email);
                i.putExtra("no",no);
                startActivity(i);
            }
        });
       /* upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });*/
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        session = new SessionManager(getApplicationContext());
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //String filePath = cursor.getString(columnIndex);
            cursor.close();
            String filePath = Environment.getExternalStorageDirectory().toString() + "/Pictures";
            //String fileName = "someFileName.jpg";

           // File f = new File(filePath,filename);
            if(TextUtils.isEmpty(filePath))
                return;
            File file = new File(filePath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

            Log.d("THIS", data.getData().getPath());
            token = session.getAccesToken();
            id = session.getId();
            String tokenize = "Bearer " + token;

            //retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
            Call<ResponseUploadAvatar> req = UtilsApi.getAPIService().postImage(tokenize, id, body);
            req.enqueue(new Callback<ResponseUploadAvatar>() {
                @Override
                public void onResponse(Call<ResponseUploadAvatar> call, Response<ResponseUploadAvatar> response) {
                    Toast.makeText(ProfileActivity.this,"Berhasil Upload Foto",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseUploadAvatar> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }*/

    private void getProfile() {
        session = new SessionManager(getApplicationContext());
        token = session.getAccesToken();
        String tokenize = "Bearer " + token;
        Call<ResponseProfile> call = UtilsApi.getAPIService().getProfile(tokenize);

        call.enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if (response.isSuccessful()) {
                    ResponseProfile responsesProfile = response.body();
                    nama = responsesProfile.getNama();
                    posisi = responsesProfile.getPosisi();
                    email = responsesProfile.getEmail();
                    no = responsesProfile.getNoHp();

                    mNama.setText(nama);
                    mPosisi.setText(posisi);
                    mEmail.setText(email);
                    mHp.setText(no);
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Silakan Login kembali", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ProfileActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
