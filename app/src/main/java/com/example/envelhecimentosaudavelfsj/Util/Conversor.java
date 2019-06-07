package com.example.envelhecimentosaudavelfsj.Util;

import android.arch.persistence.room.TypeConverter;

import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.google.gson.Gson;

import java.util.Date;

public class Conversor {

    @TypeConverter
    public static Date fromLong(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String enderecoToString(Endereco endereco){
        return new Gson().toJson(endereco);
    }

    @TypeConverter
    public static Endereco stringToEndereco(String enderecoString){
        return new Gson().fromJson(enderecoString, Endereco.class);
    }
}