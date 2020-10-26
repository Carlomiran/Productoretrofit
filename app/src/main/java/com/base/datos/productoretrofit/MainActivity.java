package com.base.datos.productoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.datos.productoretrofit.interfases.ProductoApi;
import com.base.datos.productoretrofit.models.Producto;
import com.bumptech.glide.Glide;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

        Button btnBuscar;
        Button btnAgregar;
        Button btnModificcar;
        Button btnEliminar;
        EditText  edtcodigo,edtpronombre,edtpropreciocosto,edtproprecioventa,edtproexistencia,edtfnentrada,edtdescripcion,edtcodigomarca,edtmarca;
        ImageView tvimgproducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtcodigo=(EditText)findViewById(R.id.codigo);
        edtpronombre=(EditText)findViewById(R.id.nombre);
        edtpropreciocosto=(EditText)findViewById(R.id.preciocosto);
        edtproprecioventa=(EditText)findViewById(R.id.precioventa);
        edtproexistencia=(EditText)findViewById(R.id.existencia);
        edtfnentrada=(EditText)findViewById(R.id.fn_entrada);
        edtdescripcion=(EditText)findViewById(R.id.descripciion);
        edtcodigomarca=(EditText)findViewById(R.id.codigo_marca);
        edtmarca=(EditText)findViewById(R.id.marca);

        tvimgproducto=(ImageView) findViewById(R.id.imag);

        btnBuscar=(Button)findViewById(R.id.buscar);
        btnAgregar=(Button)findViewById(R.id.agregar);
        btnModificcar=(Button)findViewById(R.id.modificar);
        btnEliminar=(Button)findViewById(R.id.eliminar);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                find(edtcodigo.getText().toString());

            }
        });

    }
            private  void  find(String codigo){
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://localhost:5001")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                ProductoApi ProductoApi = retrofit.create(com.base.datos.productoretrofit.interfases.ProductoApi.class);
                Call<Producto> call=ProductoApi.find(codigo);
                call.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                                try {
                                        if(response.isSuccessful()){Producto p=response.body(); String URL_IMG="https://localhost:5001"+p.getPro_codigo()+ ".jpg";
                                            Glide.with(getApplication()).load(URL_IMG).into(tvimgproducto);
                                        edtpronombre.setText(p.getPro_nombre());
                                        edtpropreciocosto.setText(p.getPro_preciocosto().toString());
                                        edtproprecioventa.setText(p.getPro_precioventa().toString());
                                        edtproexistencia.setText(p.getPro_existencia());
                                        edtfnentrada.setText(p.getPro_fn_entrada());
                                        edtdescripcion.setText(p.getPro_descripcion());
                                        edtcodigomarca.setText(p.getPro_codigo_marca());
                                        edtmarca.setText(p.getPro_marca());


                                    }
                                }catch (Exception ex){
                                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error de Conexion", Toast.LENGTH_SHORT).show();
                    }
                });

            }
}