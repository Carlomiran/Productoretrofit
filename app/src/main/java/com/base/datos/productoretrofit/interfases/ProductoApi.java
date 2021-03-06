package com.base.datos.productoretrofit.interfases;

import com.base.datos.productoretrofit.models.Producto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoApi {

    @GET("api/producto{id}")
    public Call<Producto>find(@Path("id")String id);

}
