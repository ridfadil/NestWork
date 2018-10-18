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

import java.util.ArrayList;
import java.util.List;

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
        Call<List<ResponseRiwayatCuti>> call = UtilsApi.getAPIService().getOneCuti("Bearer "+accesToken, email);
        call.enqueue(new Callback<List<ResponseRiwayatCuti>>() {
            @Override
            public void onResponse(Call<List<ResponseRiwayatCuti>> call, Response<List<ResponseRiwayatCuti>> response) {
                Toast.makeText(getActivity(), "Sedang Get Riwayat", Toast.LENGTH_SHORT).show();
                if (response.code()==200){
                    List<ResponseRiwayatCuti> responsCuti = response.body();
                    for(int i = 0; i<responsCuti.size(); i++){
                        String respons = responsCuti.get(i).getAkhirCuti();
                        String status = responsCuti.get(i).getStatus();
                        String id = responsCuti.get(i).getId();
                        String email = responsCuti.get(i).getEmail();
                        String awalCuti = responsCuti.get(i).getAwalCuti();
                        String nama = responsCuti.get(i).getNama();
                        String akhirCuti = responsCuti.get(i).getAkhirCuti();
                        String keterangan = responsCuti.get(i).getKeterangan();
                        String createdAt = responsCuti.get(i).getCreatedAt();
                        String updatedAt = responsCuti.get(i).getUpdatedAt();
                        int v = responsCuti.get(i).getV();
                        listRiwayatCuti.add(new ResponseRiwayatCuti(respons, status, id, email, awalCuti, akhirCuti, keterangan,nama,createdAt, updatedAt, v));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else {
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
        mAdapter = new RiwayatCutiAdapter(getActivity(),listRiwayatCuti);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

}
