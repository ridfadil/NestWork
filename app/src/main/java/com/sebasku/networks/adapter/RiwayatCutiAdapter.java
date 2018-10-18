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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sebasku.networks.R;
import com.sebasku.networks.activity.MenuActivity;
import com.sebasku.networks.api.UtilsApi;
import com.sebasku.networks.apimodel.ResponseDeleteCuti;
import com.sebasku.networks.apimodel.ResponseRiwayatCuti;
import com.sebasku.networks.session.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fadil on 3/17/18.
 */

public class RiwayatCutiAdapter extends RecyclerView.Adapter<RiwayatCutiAdapter.ListCutiViewHolder> {
    private List<ResponseRiwayatCuti> listCuti;
    //deklarasi global variabel
    private Context context;

    //konstruktor untuk menerima data adapter
    public RiwayatCutiAdapter(Context context, List<ResponseRiwayatCuti> listCuti) {
        this.context = context;
        this.listCuti = listCuti;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public ListCutiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cuti, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ListCutiViewHolder(mItemView, this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(ListCutiViewHolder holder, int position) {
        SessionManager session;
        final String respon, status;

        session = new SessionManager(context);
        final ResponseRiwayatCuti mCurrent = listCuti.get(position);

        //String sDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
        String awal = mCurrent.getAwalCuti();
        String akhir = mCurrent.getAkhirCuti();
        Toast.makeText(context,"ini awal "+awal,Toast.LENGTH_SHORT).show();
        Toast.makeText(context,"ini akhir "+akhir,Toast.LENGTH_SHORT).show();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z");
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        try {
            Date awalDate = dateFormat.parse(awal);
            Date akhirDate = dateFormat.parse(akhir);
            dateFormat = new SimpleDateFormat("dd MMM yyyy");
            dateFormat.setTimeZone(tz);
            holder.awalCuti.setText(dateFormat.format(awalDate));
            holder.akhirCuti.setText(dateFormat.format(akhirDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        // TODO : benarkan Format Date di cuti, Request Date , Benarkan Convert Date
        holder.awalCuti.setText(mCurrent.getAwalCuti());
        holder.akhirCuti.setText(mCurrent.getAkhirCuti());
        holder.keterangan.setText(mCurrent.getKeterangan());
        //holder.tlKet.setText("Keterangan : ");
        respon = mCurrent.getRespons();
        status = mCurrent.getStatus();
        if (status.equals("3")) {
            holder.gambar.setImageResource(R.drawable.waiting);
        } else if (status.equals("0")) {
            holder.gambar.setImageResource(R.drawable.ic_decline);
        } else if (status.equals("1")) {
            holder.gambar.setImageResource(R.drawable.ic_check);
        }
        holder.tlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ini respon : " + respon + " inistatus : " + status, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ListCutiViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        final RiwayatCutiAdapter mAdapter;
        private TextView awalCuti;
        private TextView akhirCuti;
        private TextView keterangan;
        private TextView tlKet;
        private CardView tlCard;
        private TextView tlAwalCuti;
        private TextView tlAkhirCuti;
        private ImageView gambar;

        //untuk casting view yang digunakan pada list item
        public ListCutiViewHolder(View itemView, RiwayatCutiAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            awalCuti = itemView.findViewById(R.id.tv_awal_cuti);
            akhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            keterangan = itemView.findViewById(R.id.tv_keterangan);
            tlAwalCuti = itemView.findViewById(R.id.tl_awal_cuti);
            tlAkhirCuti = itemView.findViewById(R.id.tv_akhir_cuti);
            gambar = itemView.findViewById(R.id.iv_terima_atau_tolak);
            tlCard = itemView.findViewById(R.id.cd_view);
            tlKet = itemView.findViewById(R.id.tl_keterangan);
            tlCard.setOnLongClickListener(this);
            this.mAdapter = adapter;
            tlCard.setLongClickable(true);
            tlCard.setClickable(true);
            //itemView.setOnClickListener(this);
        }

        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final ResponseRiwayatCuti element = listCuti.get(mPosition);

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
            return true;
        }

        private void hapusData(String id) {
            int mPosition = getLayoutPosition();
            ResponseRiwayatCuti element = listCuti.get(mPosition);
            SessionManager userPref = new SessionManager(context);
            String accesToken = userPref.getAccesToken();
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteCuti("Bearer " + accesToken, id);
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

