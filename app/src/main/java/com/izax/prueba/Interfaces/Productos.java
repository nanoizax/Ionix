package com.izax.prueba.Interfaces;

import com.izax.prueba.Mapeo.Nexo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Productos {

    @GET("en/API/InTheaters/k_4ggkrp0h")
    Call<Nexo> getProductos();

}
