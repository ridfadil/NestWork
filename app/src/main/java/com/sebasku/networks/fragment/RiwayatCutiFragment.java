package com.sebasku.networks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.activity.LoginActivity;
import com.sebasku.networks.adapter.RiwayatCutiAdapter;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.ResponseRiwayatCuti;
import com.sebasku.networks.session.SessionManager;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatCutiFragment extends Fragment {
    View v;
    private List<ResponseRiwayatCuti> listRiwayatCuti;
    private RecyclerView mRecyclerView;
    private RiwayatCutiAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_riwayat_cuti, container, false);
        listRiwayatCuti = new ArrayList<>();

        final SessionManager userPref = new SessionManager(getContext());
        String accesToken = userPref.getAccesToken();
        String email = userPref.getEmail();
        Call<List<ResponseRiwayatCuti>> call = UtilsApi.getAPIService().getOneCuti("Bearer " + accesToken, email);
        call.enqueue(new Callback<List<ResponseRiwayatCuti>>() {
            @Override
            public void onResponse(Call<List<ResponseRiwayatCuti>> call, Response<List<ResponseRiwayatCuti>> response) {
                // Toast.makeText(getActivity(), "Sedang Get Riwayat", Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    List<ResponseRiwayatCuti> responsCuti = response.body();
                    for (int i = 0; i < responsCuti.size(); i++) {
                        String respons = responsCuti.get(i).getAkhirCuti();
                        String status = responsCuti.get(i).getStatus();
                        String id = responsCuti.get(i).getId();
                        String email = responsCuti.get(i).getEmail();

                        String awalCuti = responsCuti.get(i).getAwalCuti();
                        String splitAwal[] = awalCuti.split(" ");
                        String cutiAwal = changeFormat(splitAwal);
                        String dateAwal = changDateFormat(cutiAwal);

                        String nama = responsCuti.get(i).getNama();

                        String akhirCuti = responsCuti.get(i).getAkhirCuti();
                        String splitAkhir[] = akhirCuti.split(" ");
                        String cutiAkhir = changeFormat(splitAkhir);

                        String keterangan = responsCuti.get(i).getKeterangan();
                        String createdAt = responsCuti.get(i).getCreatedAt();
                        String updatedAt = responsCuti.get(i).getUpdatedAt();
                        int v = responsCuti.get(i).getV();
                        listRiwayatCuti.add(new ResponseRiwayatCuti(respons, status, id, email, dateAwal, cutiAkhir, keterangan, nama, createdAt, updatedAt, v));
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "Salah di riwayat Cuti", Toast.LENGTH_SHORT).show();
                  /*  Intent i = new Intent(getActivity().getApplication(),LoginActivity.class);
                    startActivity(i);*/
                }
            }

            @Override
            public void onFailure(Call<List<ResponseRiwayatCuti>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error Connection", Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView = v.findViewById(R.id.rv_riwayat_cuti);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RiwayatCutiAdapter(getActivity(), listRiwayatCuti);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public String changeFormat(String splitAwal[]) {
        String hariAwal = splitAwal[0];
        String bulanAwal = splitAwal[1];
        String tglAwal = splitAwal[2];
        String thnAwal = splitAwal[3];
        String result = hariAwal + " " + tglAwal + " " + bulanAwal + " " + thnAwal;
        return result;
    }

    public String changDateFormat(String cutiAwal) {
        String sDate = cutiAwal;
        String formatCutiAwal = "";
        SimpleDateFormat dateFormat;
        dateFormat= new SimpleDateFormat("EEE dd MMM yyyy",Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        try {
            Date newDate = dateFormat.parse(sDate);
            dateFormat = new SimpleDateFormat("EEE dd MMM yyyy",Locale.ENGLISH);
            dateFormat.setTimeZone(tz);
            formatCutiAwal = dateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatCutiAwal;
    }


    /*SimpleDateFormat formatIncoming =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    SimpleDateFormat formatOutgoing = new SimpleDateFormat("yyyy-MM-dd");
    TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
System.out.println(tz.getDisplayName(false,TimeZone.SHORT,Locale.ENGLISH)); // WIB

formatOutgoing.setTimeZone(tz);
    String s = formatOutgoing.format(formatIncoming.parse("Tue Mar 03 00:00:00 WIB 2015"));

System.out.println("Date in Indonesia: "+s); // 2015-03-03*/
}
