package com.izax.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import com.google.android.material.snackbar.Snackbar;
import com.izax.prueba.Adaptadores.ADEstaciones;
import com.izax.prueba.Interfaces.Productos;
import com.izax.prueba.Interfaces.RetrofitIntenace;
import com.izax.prueba.Mapeo.MProductos;
import com.izax.prueba.Mapeo.Nexo;
import com.izax.prueba.Util.Funciones;
import com.izax.prueba.Util.Objetos;
import com.izax.prueba.Util.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f.GPSPermiso(this);

        f.CamaraPermiso(this);

        OBJETOS();

        find();

        BOTONES();


    }

    Funciones f = new Funciones();
    Objetos o = new Objetos();

    List<MProductos> mProductos = new ArrayList<>();
    List<Nexo> mNexo;
    ADEstaciones adEstaciones;

    private RecyclerView.LayoutManager layoutManager;

    private void OBJETOS(){

        o.rv_home = findViewById(R.id.rv_home);
        o.txt_buscador = findViewById(R.id.txt_buscardor);
        o.srl = findViewById(R.id.swiperlayout);

        mProductos = new ArrayList<>();
        o.rv_home.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void BOTONES(){

        o.txt_buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence se, int i, int i1, int i2) {

                adEstaciones.filter(se.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        o.srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                find();

                adEstaciones.notifyDataSetChanged();

                o.srl.setRefreshing(false);
            }
        });

    }

    private void find(){

        mProductos.clear();

        Productos productos = RetrofitIntenace.getRetrofit().create(Productos.class);

        Call<Nexo> call = productos.getProductos();

        call.enqueue(new Callback<Nexo>() {
            @Override
            public void onResponse(Call<Nexo> call, Response<Nexo> response) {

                try {

                    if(!response.isSuccessful()){
                        return;
                    }

                    mProductos = response.body().getmData();

                    //Log.d("RESPUESTA 1", response.body().getmData().toString());

                    adEstaciones = new ADEstaciones(MainActivity.this, mProductos);

                    o.rv_home.setAdapter(adEstaciones);

                    ItemTouchHelper.SimpleCallback simpleCallback =
                            new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, MainActivity.this);

                    new ItemTouchHelper(simpleCallback).attachToRecyclerView(o.rv_home);

                }catch (Exception e){
                    f.mensaje(MainActivity.this, e.toString());
                }
            }

            @Override
            public void onFailure(Call<Nexo> call, Throwable t) {
                f.mensaje(MainActivity.this, "Error");
            }
        });
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof ADEstaciones.NewsViewHolder){


            String nombre = mProductos.get(viewHolder.getAdapterPosition()).getTitle();
            final MProductos peliculaBorrado = mProductos.get(viewHolder.getAdapterPosition());
            final int deletedIntex = viewHolder.getAdapterPosition();

            adEstaciones.removeItem(viewHolder.getAdapterPosition());

            recuperarCocheBorrado(viewHolder,nombre,peliculaBorrado,deletedIntex);

        }
    }

    private void recuperarCocheBorrado(RecyclerView.ViewHolder viewHolder,String nombre,
                                       final MProductos peliculaBorrado, final int deletedIntex){

        Snackbar snackbar = Snackbar.make(((ADEstaciones.NewsViewHolder)viewHolder).c_estaciones,
                nombre + " eliminado", Snackbar.LENGTH_LONG);
        snackbar.setAction("Deshacer", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adEstaciones.restoreItem(peliculaBorrado, deletedIntex);
            }
        });

        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }



}