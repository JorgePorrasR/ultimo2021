package mx.com.systemjorge.mascotasapp.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import mx.com.systemjorge.mascotasapp.Adapters.RecyclerAdapter;
import mx.com.systemjorge.mascotasapp.Adapters.RecyclerLugares;
import mx.com.systemjorge.mascotasapp.Adapters.RecyclerNoticias;
import mx.com.systemjorge.mascotasapp.FechaVo;
import mx.com.systemjorge.mascotasapp.LoginActivity;
import mx.com.systemjorge.mascotasapp.NoticiasVo;
import mx.com.systemjorge.mascotasapp.PerdidoVo;
import mx.com.systemjorge.mascotasapp.Providers.AuthProvider;
import mx.com.systemjorge.mascotasapp.Providers.FechasProvider;
import mx.com.systemjorge.mascotasapp.Providers.NoticiasProvider;
import mx.com.systemjorge.mascotasapp.Providers.PerritosProvider;
import mx.com.systemjorge.mascotasapp.Providers.PubliProvider;
import mx.com.systemjorge.mascotasapp.R;
import mx.com.systemjorge.mascotasapp.cargaActivity;

import androidx.appcompat.widget.Toolbar;

public class PrimerActivity extends AppCompatActivity {

    private Button btnPerdidos, btnNoticias, btnAdopcion, btnLogout, btn4;
    private int action;
    //Comentario chido
    //Providers
    private PerritosProvider mPerritosProvider;
    private NoticiasProvider mNoticiasProvider;
    private FechasProvider mFechasProvider;
    private AuthProvider mAuthProvider;
    private PubliProvider mPubliProvider;


    private Dialog publicidad;
    private Dialog carga;
    private ImageView publicidad2;
    private Button close;
    private PerdidoVo model;
    private NoticiasVo model2;
    private FechaVo model3;
    private List<FechaVo> modelList3 = new ArrayList<>();
    private List<PerdidoVo> modelList = new ArrayList<>();
    private List<NoticiasVo> modelList2 = new ArrayList<>();
    private TextView titulo;
    private RecyclerView mRecycler;
    private RecyclerAdapter mAdapter;
    private ImageView logo;
    private RecyclerView mRecycler2;
    private RecyclerNoticias mAdapter2;
    private RelativeLayout ll;
    private RecyclerView mRecycler3;
    private RecyclerLugares mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        btnPerdidos = findViewById(R.id.btnList);
        ll = findViewById(R.id.linearPets);
        ll.setBackgroundResource(R.drawable.color_fondo);
        logo = findViewById(R.id.imageView);
        btnNoticias = findViewById(R.id.btnMenu);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titulo = findViewById(R.id.Titulo);
        btnAdopcion = findViewById(R.id.btnAdopccion);
        btn4 = findViewById(R.id.btn_regresar);
        btnLogout = findViewById(R.id.btn_logout);
        carga = new Dialog(this);
        carga.setContentView(R.layout.dialog_carga2);
        btn4.setVisibility(View.INVISIBLE);
        toolbar.setVisibility(View.INVISIBLE);
        btnPerdidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    list1();
                action = 1;
                ll.setBackgroundResource(R.drawable.color_fondo2);
                logo.setVisibility(View.INVISIBLE);
                toolbar.setBackgroundResource(R.drawable.color_fondo3);
                toolbar.setVisibility(View.VISIBLE);
                btnNoticias.setVisibility(View.INVISIBLE);
                btnPerdidos.setVisibility(View.INVISIBLE);
                btnAdopcion.setVisibility(View.INVISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.VISIBLE);
                titulo.setText("Perdidos");
            }

        });



        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNoticias();
                action = 2;
                toolbar.setBackgroundResource(R.drawable.color_fondo3);
                ll.setBackgroundResource(R.drawable.color_fondo2);
                btn4.setVisibility(View.VISIBLE);
                logo.setVisibility(View.INVISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                btnPerdidos.setVisibility(View.INVISIBLE);
                btnNoticias.setVisibility(View.INVISIBLE);
                btnAdopcion.setVisibility(View.INVISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                titulo.setText("Noticias");

                //Toast.makeText(PrimerActivity.this, "Lista 2", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { list2();
                action = 1;
                ll.setBackgroundResource(R.drawable.color_fondo2);
                toolbar.setBackgroundResource(R.drawable.color_fondo3);
                logo.setVisibility(View.INVISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btnPerdidos.setVisibility(View.INVISIBLE);
                btnNoticias.setVisibility(View.INVISIBLE);
                btnAdopcion.setVisibility(View.INVISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                titulo.setText("Adopción");
                //Toast.makeText(PrimerActivity.this, "Lista 3", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action == 1) {
                    borrarDatos();
                    ll.setBackgroundResource(R.drawable.color_fondo);
                    logo.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.INVISIBLE);
                    btnPerdidos.setVisibility(View.VISIBLE);
                    btnNoticias.setVisibility(View.VISIBLE);
                    btnAdopcion.setVisibility(View.VISIBLE);
                    btnLogout.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }
                if(action == 2){
                    borrarDatos2();
                    ll.setBackgroundResource(R.drawable.color_fondo);
                    logo.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.INVISIBLE);
                    btnPerdidos.setVisibility(View.VISIBLE);
                    btnNoticias.setVisibility(View.VISIBLE);
                    btnAdopcion.setVisibility(View.VISIBLE);
                    btnLogout.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.INVISIBLE);
                }

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        //Instancia de los providers
        mPerritosProvider = new PerritosProvider();
        mNoticiasProvider = new NoticiasProvider();
        mAuthProvider = new AuthProvider();

        mRecycler = findViewById(R.id.recyclerExample);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setHasFixedSize(true);

        mRecycler2 = findViewById(R.id.recyclerExample);
        mRecycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler2.setHasFixedSize(true);


    } // -- FIN DEL MÉTODO onCreate -- //

    @Override
    protected void onPause() {
        super.onPause();

        if (publicidad.isShowing()) {
            publicidad.cancel();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (publicidad.isShowing()) {
            publicidad.cancel();
        }

    }

    protected void onStart() {
        super.onStart();

        mPubliProvider = new PubliProvider();

        publicidad = new Dialog(this);
        publicidad.setContentView(R.layout.dialog_publicidad);
        publicidad.setCancelable(false);
        publicidad.setCanceledOnTouchOutside(false);
        publicidad2 = publicidad.findViewById(R.id.ImagePubli);
        carga = new Dialog(this);
        carga.setContentView(R.layout.dialog_carga2);
        close = publicidad.findViewById(R.id.closepubli);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicidad.dismiss();
            }
        });
        publicidad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //aderir un escuchador de evento por una unica ocacion
        mPubliProvider.getImagenes().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //de la inf que obtuvo se validara si snapshot exite
                if(snapshot.exists()){
                    String imagen;
                    int mNumber = (int) (Math.random() * 6) + 1;
                    switch (mNumber){
                        case 1:

                            imagen = snapshot.child("imagen1").getValue().toString();
                            setPublicidad(imagen);

                            break;
                        case 2:
                            imagen = snapshot.child("imagen2").getValue().toString();
                            setPublicidad(imagen);
                            break;
                        case 3:
                            imagen = snapshot.child("imagen3").getValue().toString();
                            setPublicidad(imagen);
                            break;

                        case 4:
                            imagen = snapshot.child("imagen4").getValue().toString();
                            setPublicidad(imagen);
                            break;
                        case 5:
                            imagen = snapshot.child("imagen5").getValue().toString();
                            setPublicidad(imagen);
                            break;
                        case 6:
                            imagen = snapshot.child("imagen6").getValue().toString();
                            setPublicidad(imagen);
                            break;

                        default:
                            Log.d("JRP_DEBUG","Algo ha salido mal a la hora de crear un numero aleatorio");
                            break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void setPublicidad(String image){

        Glide
                .with(PrimerActivity.this)
                .load(image)
                //.placeholder(R.drawable.loading_spinner)
                .into(publicidad2);





        publicidad.show();

    }
    private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 25,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);}
    private void logout() {
        mAuthProvider.logout(); // Cerrramos sesión
        Intent intent = new Intent(PrimerActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void list1() {
        //Validaremos si hay conexión a internet xd
        ConnectivityManager connection = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        if(network != null && network.isConnected()){
            getPersonajes();
        }else {
            Toast.makeText(PrimerActivity.this, "Usted no tiene acceso a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void list2() {
        //Validaremos si hay conexión a internet xd
        ConnectivityManager connection = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        if(network != null && network.isConnected()){
            getPersonajes2();
        }else {
            Toast.makeText(PrimerActivity.this, "Usted no tiene acceso a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void listNoticias() {
        //Validaremos si hay conexión a internet xd
        ConnectivityManager connection = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        if(network != null && network.isConnected()){
            getNoticias();
        }else {
            Toast.makeText(PrimerActivity.this, "Usted no tiene acceso a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPersonajes() {
        mPerritosProvider.getPerritos().child("perros").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        model = dataSnapshot1.getValue(PerdidoVo.class);
                        modelList.add(model);
                    }
                    //Cuando el Foreach haya terminado de pasar la información, llenaremos el recycler.
                    displayPerritos(modelList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SMM_DEBUG", "Error: " + error.getMessage());
            }
        });
    }
    private void getLugares() {
        mFechasProvider.getLugares().child("lugares").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        model3 = dataSnapshot1.getValue(FechaVo.class);
                        modelList3.add(model3);
                    }
                    //Cuando el Foreach haya terminado de pasar la información, llenaremos el recycler.
                    displayFechas(modelList3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SMM_DEBUG", "Error: " + error.getMessage());
            }
        });
    }

    private void getNoticias() {
        mNoticiasProvider.getNoticias().child("noticias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        model2 = dataSnapshot1.getValue(NoticiasVo.class);
                        modelList2.add(model2);
                    }
                    //Cuando el Foreach haya terminado de pasar la información, llenaremos el recycler.
                    displayNoticias(modelList2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SMM_DEBUG", "Error: " + error.getMessage());
            }
        });
    }
    private void getPersonajes2() {
        mPerritosProvider.getPerritos().child("perrosAlbergues").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        model = dataSnapshot1.getValue(PerdidoVo.class);
                        modelList.add(model);
                    }
                    //Cuando el Foreach haya terminado de pasar la información, llenaremos el recycler.
                    displayPerritos(modelList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("SMM_DEBUG", "Error: " + error.getMessage());
            }
        });
    }
    public void borrarDatos(){
        modelList.clear();
        mAdapter.notifyDataSetChanged();
    }
    public void borrarDatos2(){
        modelList2.clear();
        mAdapter2.notifyDataSetChanged();
    }
    public void borrarDatos3(){
        modelList.clear();
        mAdapter.notifyDataSetChanged();
    }
    private void displayPerritos(List<PerdidoVo> modelList) {
        mAdapter = new RecyclerAdapter(this, modelList);
        mRecycler.setAdapter(mAdapter);
    }
    private void displayNoticias(List<NoticiasVo> modelList2) {
        mAdapter2 = new RecyclerNoticias(this, modelList2);
        mRecycler2.setAdapter(mAdapter2);
    }
    private void displayFechas(List<FechaVo> modelList3) {
        mAdapter3 = new RecyclerLugares(this, modelList3);
        mRecycler3.setAdapter(mAdapter3);
    }
}