package com.sebasku.networks.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.activity.EditProfilActivity;
import com.sebasku.networks.activity.LoginActivity;
import com.sebasku.networks.activity.MenuActivity;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.RequestCutiForm;
import com.sebasku.networks.apimodel.ResponseAjukanCuti;
import com.sebasku.networks.session.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCutiFragment extends Fragment  {
    View viewRequestCuti;
    Button requestCuti;
    EditText awalCuti, akhirCuti, keterangan;
    String mAwalCuti = "", mAkhirCuti = "", mKeterangan = "";
    TextView tgl;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRequestCuti = inflater.inflate(R.layout.fragment_request_cuti, container, false);
        init();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        tgl.setText(currentDateTimeString);

        requestCuti.setOnClickListener(view -> {
            if (!awalCuti.getText().toString().equals("") && !akhirCuti.getText().toString().equals("") && !keterangan.getText().toString().equals("")) {
                mAwalCuti = awalCuti.getText().toString();
                mAkhirCuti = akhirCuti.getText().toString();
                mKeterangan = keterangan.getText().toString();
                String respons = "0";
                String status = "3";
                session = new SessionManager(getContext());
                String nama = session.getNama();
                String mEmail = session.getEmail();
                saveCuti(mEmail, nama, mAwalCuti, mAkhirCuti, mKeterangan, respons, status);
            } else {
                Toast.makeText(getActivity(), "Maaf Form Masih ada yang kosong", Toast.LENGTH_SHORT).show();
            }
        });


        akhirCuti.setOnClickListener(view -> {
            Calendar newCalendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                mAkhirCuti = dateFormat.format(newDate.getTime());
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
                akhirCuti.setText(dateFormatter.format(newDate.getTime()));
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        awalCuti.setOnClickListener(view -> {
            Calendar newCalendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, monthOfYear, dayOfMonth) -> {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                mAwalCuti = dateFormat.format(newDate.getTime());
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
                awalCuti.setText(dateFormatter.format(newDate.getTime()));
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        return viewRequestCuti;
    }

    public void init() {
        awalCuti =  viewRequestCuti.findViewById(R.id.et_awal_cuti);
        akhirCuti =  viewRequestCuti.findViewById(R.id.et_akhir_cuti);
        keterangan =  viewRequestCuti.findViewById(R.id.et_keterangan_cuti);
        requestCuti =  viewRequestCuti.findViewById(R.id.btn_request_cuti);
        tgl = viewRequestCuti.findViewById(R.id.tv_today_tglcuti);
    }

    public void saveCuti(String email, String nama, String awalCuti, String akhirCuti, String keterangan, String respons, String status) {
        //String a="02:02:2018",c="05:07:2019";
        RequestCutiForm cuti = new RequestCutiForm(email, nama, awalCuti, akhirCuti, keterangan, respons, status);
        // User responsesId = response.body().getUser();
        session = new SessionManager(getContext());
        String token = session.getAccesToken();
        /*        Toast.makeText(getActivity(), "ini token : "+token, Toast.LENGTH_SHORT).show();*/
        String b = "Bearer ";
        String tokenize = b + token;
        Call<ResponseAjukanCuti> call = UtilsApi.getAPIService().addCuti(tokenize, cuti);

        call.enqueue(new Callback<ResponseAjukanCuti>() {
            @Override
            public void onResponse(Call<ResponseAjukanCuti> call, Response<ResponseAjukanCuti> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Sukses Ajukan Cuti", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity().getApplication(), MenuActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(), "Salah di Request", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAjukanCuti> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}