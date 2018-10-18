package com.sebasku.networks.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sebasku.networks.R;
import com.sebasku.networks.activity.DetailGajiActivity;
import com.sebasku.networks.activity.MenuActivity;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.ResponseRiwayatGaji;
import com.sebasku.networks.session.SessionManager;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fadil on 3/19/18.
 */

public class RiwayatGajiAdapter extends RecyclerView.Adapter<RiwayatGajiAdapter.ListGajiViewHolder> {
    private List<ResponseRiwayatGaji> listGaji;
    //deklarasi global variabel
    private Context context;

    //konstruktor untuk menerima data adapter
    public RiwayatGajiAdapter(Context context, List<ResponseRiwayatGaji> listGaji) {
        this.context = context;
        this.listGaji = listGaji;

    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListGajiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_gaji, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListGajiViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListGajiViewHolder holder, int position) {
        final ResponseRiwayatGaji mCurrent = listGaji.get(position);
        final SessionManager session = new SessionManager(context);
        final String nama = session.getNama();
        final String posisi = session.getPosisi();
        final String email = session.getEmail();
        final String task = mCurrent.getJumlahTask();
        final String gaji = mCurrent.getGaji();

        String mSesi = "";
        String tgl = mCurrent.getCreatedAt();
        String[]parts = tgl.split("T");
        String tgls = parts[0];
        String[]partss = tgls.split("-");
        String tglss = partss[2];
        String bln = partss[1];
        int tanggal = Integer.parseInt(tglss);
        int bulan = Integer.parseInt(bln);

        if (bulan == 1){
            mSesi = mSesi+"Januari ";
        }
        else if (bulan == 2){
            mSesi = mSesi+"Februari ";

        }
        else if (bulan == 3){
            mSesi = mSesi+"Maret ";

        }
        else if (bulan == 4){
            mSesi = mSesi+"April ";

        }
        else if (bulan == 5){
            mSesi = mSesi+"Mei ";

        }
        else if (bulan == 6){
            mSesi = mSesi+"Juni ";

        }
        else if (bulan == 7){
            mSesi = mSesi+"Juli ";

        }
        else if (bulan == 8){
            mSesi = mSesi+"Agustus ";

        }
        else if (bulan == 9){
            mSesi = mSesi+"September ";

        }
        else if (bulan == 10){
            mSesi = mSesi+"Oktober ";

        }
        else if (bulan == 11){
            mSesi = mSesi+"November ";

        }
        else if (bulan == 12){
            mSesi = mSesi+"Desember ";

        }

        if (tanggal<=15){
            mSesi = mSesi+"Sesi 1";
            holder.sesi.setText(mSesi);
        }
        else if (tanggal > 15){
            mSesi = mSesi+"Sesi 2";
            holder.sesi.setText(mSesi);
        }

        final String ses=mSesi;
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailGajiActivity.class);
                i.putExtra("sesi", ses);
                i.putExtra("nama", nama);
                i.putExtra("email", email);
                i.putExtra("posisi", posisi);
                i.putExtra("task", task);
                i.putExtra("gaji", gaji);
                context.startActivity(new Intent(i));
            }
        });
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listGaji.size();
    }

    public class ListGajiViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        final RiwayatGajiAdapter mAdapter;
        private TextView sesi;
        private TextView totalGaji;
        private TextView jumlahTask;
        private TextView posisi;
        private CardView cdView;
        private TextView nama;
        private Button btnDetail;

        //untuk casting view yang digunakan pada list item
        public ListGajiViewHolder(View itemView, RiwayatGajiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            sesi = itemView.findViewById(R.id.tv_info_sesi);
            totalGaji = itemView.findViewById(R.id.tv_akhir_cuti);
            jumlahTask = itemView.findViewById(R.id.tv_jum_task);
            posisi = itemView.findViewById(R.id.tv_posisi);
            nama = itemView.findViewById(R.id.tv_nama);
            cdView = itemView.findViewById(R.id.cd_view);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            itemView.setOnLongClickListener(this);
            this.mAdapter = adapter;
            //itemView.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final ResponseRiwayatGaji element = listGaji.get(mPosition);

            //alert dialog
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Hapus data cuti ini?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            hapusData(element.getId());
                        }
                    });

            builder1.setNegativeButton(
                    "Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }

        private void hapusData(String id) {
            int mPosition = getLayoutPosition();
            ResponseRiwayatGaji element = listGaji.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteGaji("Bearer " + accesToken, id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    mAdapter.notifyDataSetChanged();
                    Intent i = new Intent(context, MenuActivity.class);
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();

                    context.startActivities(new Intent[]{i});
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mAdapter.notifyDataSetChanged();
                    Intent i = new Intent(context, MenuActivity.class);
                    Toast.makeText(context, "Data Gagal dihapus", Toast.LENGTH_SHORT).show();
                    context.startActivities(new Intent[]{i});
                }
            });
        }
    }
}
