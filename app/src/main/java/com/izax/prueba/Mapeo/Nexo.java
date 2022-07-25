package com.izax.prueba.Mapeo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Nexo {

    @SerializedName("items")
    private List<MProductos> mData;

    public List<MProductos> getmData() {
        return mData;
    }





}
