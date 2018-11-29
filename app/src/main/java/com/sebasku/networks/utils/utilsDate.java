package com.sebasku.networks.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class utilsDate {
    public String dateFormatter(String tanggal) {
        String mHari = "", mBulan = "";
        List<String> date = new ArrayList<>();

        date = Arrays.asList(tanggal.split(" "));
        if (date.get(0).equalsIgnoreCase("Mon")) {
            mHari = "Minggu";
        } else if (date.get(0).equalsIgnoreCase("Sun")) {
            mHari = "Senin";
        } else if (date.get(0).equalsIgnoreCase("Tue")) {
            mHari = "Selasa";
        } else if (date.get(0).equalsIgnoreCase("Wed")) {
            mHari = "Rabu";
        } else if (date.get(0).equalsIgnoreCase("Thu")) {
            mHari = "Kamis";
        } else if (date.get(0).equalsIgnoreCase("Fri")) {
            mHari = "Jumat";
        } else if (date.get(0).equalsIgnoreCase("Sat")) {
            mHari = "Sabtu";
        }

        if (date.get(2).equalsIgnoreCase("Jan")) {
            mBulan = "Januari";
        } else if (date.get(2).equalsIgnoreCase("Feb")) {
            mBulan = "Februari";
        } else if (date.get(2).equalsIgnoreCase("Mar")) {
            mBulan = "Maret";
        } else if (date.get(2).equalsIgnoreCase("Apr")) {
            mBulan = "April";
        } else if (date.get(2).equalsIgnoreCase("May")) {
            mBulan = "Mei";
        } else if (date.get(2).equalsIgnoreCase("Jun")) {
            mBulan = "Juni";
        } else if (date.get(2).equalsIgnoreCase("Jul")) {
            mBulan = "Juli";
        } else if (date.get(2).equalsIgnoreCase("Aug")) {
            mBulan = "Agustus";
        } else if (date.get(2).equalsIgnoreCase("Sep")) {
            mBulan = "September";
        } else if (date.get(2).equalsIgnoreCase("Oct")) {
            mBulan = "Oktober";
        } else if (date.get(2).equalsIgnoreCase("Nov")) {
            mBulan = "November";
        } else if (date.get(2).equalsIgnoreCase("Dec")) {
            mBulan = "Desember";
        }

        tanggal = mHari + " " + date.get(1) + " " + mBulan + " " + date.get(3);
        return tanggal;
    }
}
