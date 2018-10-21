package com.sebasku.networks.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCutiFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    View viewRequestCuti;
    Button requestCuti;
    EditText awalCuti, akhirCuti, keterangan, email;
    String mAwalCuti, mAkhirCuti, mKeterangan, mEmail;
    TextView tgl;
    int st;
    SessionManager session;
    private int mYearIni, mMonthIni, mDayIni, mYearIni2, mMonthIni2, mDayIni2;
    private int sYearIni, sMonthIni, sDayIni, sYearIni2, sMonthIni2, sDayIni2;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    Calendar C1 = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRequestCuti = inflater.inflate(R.layout.fragment_request_cuti, container, false);
        init();

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        tgl.setText(currentDateTimeString);

        requestCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        awalCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                st = 0;
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), RequestCutiFragment.this, year, month, day);
                datePickerDialog.show();
            }
        });

        akhirCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c1 = Calendar.getInstance();
                int year = c1.get(Calendar.YEAR);
                int month = c1.get(Calendar.MONTH);
                int day = c1.get(Calendar.DAY_OF_MONTH);
                st = 1;
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), RequestCutiFragment.this, year, month, day);
                datePickerDialog.show();
            }
        });


        return viewRequestCuti;
    }

    public void init() {
        awalCuti = (EditText) viewRequestCuti.findViewById(R.id.et_awal_cuti);
        akhirCuti = (EditText) viewRequestCuti.findViewById(R.id.et_akhir_cuti);
        keterangan = (EditText) viewRequestCuti.findViewById(R.id.et_keterangan_cuti);
        requestCuti = (Button) viewRequestCuti.findViewById(R.id.btn_request_cuti);
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
                   /* Intent i = new Intent(getActivity().getApplication(),LoginActivity.class);
                    startActivity(i);*/
                }
            }

            @Override
            public void onFailure(Call<ResponseAjukanCuti> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int yearFinal = i;
        int monthFinal = i1 + 1;
        int dayFinal = i2;
        if (st == 0) {
            awalCuti.setText("" + monthFinal + "-" +  dayFinal + "-" + yearFinal);
        } else if (st == 1) {
            akhirCuti.setText("" +  monthFinal + "-" + dayFinal + "-" + yearFinal);
        }
        /*        akhirCuti.setText(""+dayFinal+"-"+monthFinal+"-"+yearFinal);*/
    }
}