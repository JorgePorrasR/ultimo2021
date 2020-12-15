package mx.com.systemjorge.mascotasapp.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import mx.com.systemjorge.mascotasapp.Adapters.RecyclerNoticias;
import mx.com.systemjorge.mascotasapp.LoginActivity;
import mx.com.systemjorge.mascotasapp.NoticiasVo;
import mx.com.systemjorge.mascotasapp.PerdidoVo;
import mx.com.systemjorge.mascotasapp.Providers.AuthProvider;
import mx.com.systemjorge.mascotasapp.Providers.NoticiasProvider;
import mx.com.systemjorge.mascotasapp.Providers.PerritosProvider;
import mx.com.systemjorge.mascotasapp.Providers.PubliProvider;
import mx.com.systemjorge.mascotasapp.R;


public class PrimerActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btnLogout, btn4, btn5, btn6;
    //Comentario chido
    //Providers
    private PerritosProvider mPerritosProvider;
    private NoticiasProvider mNoticiasProvider;
    private AuthProvider mAuthProvider;
    private PubliProvider mPubliProvider;



    private Dialog publicidad;
    private ImageView publicidad2;
    private Button close;
    private PerdidoVo model;
    private NoticiasVo model2;
    private List<PerdidoVo> modelList = new ArrayList<>();
    private List<NoticiasVo> modelList2 = new ArrayList<>();

    private RecyclerView mRecycler;
    private RecyclerAdapter mAdapter;

    private RecyclerView mRecycler2;
    private RecyclerNoticias mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        btn1 = findViewById(R.id.btnList);
        btn2 = findViewById(R.id.btnMenu);
        btn3 = findViewById(R.id.btnAdopccion);
        btn4 = findViewById(R.id.btn_regresar);
        btn5 = findViewById(R.id.btn_noNoticias);
        btn6 = findViewById(R.id.btn_regresar2);
        btnLogout = findViewById(R.id.btn_logout);
        btn4.setVisibility(View.INVISIBLE);
        btn6.setVisibility(View.INVISIBLE);
        btn5.setVisibility(View.INVISIBLE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list1();
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.VISIBLE);
            }

        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list1();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarDatos2();
                btn1.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.INVISIBLE);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNoticias();
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.VISIBLE);
                //Toast.makeText(PrimerActivity.this, "Lista 2", Toast.LENGTH_SHORT).show();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { borrarDatos3();
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
            btn6.setVisibility(View.INVISIBLE);
            btn5.setVisibility(View.INVISIBLE);
            }});

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { list2();
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                //Toast.makeText(PrimerActivity.this, "Lista 3", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarDatos();
                btn6.setVisibility(View.INVISIBLE);
                btn1.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btnLogout.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.INVISIBLE);
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



    protected void onStart() {
        super.onStart();

        mPubliProvider = new PubliProvider();

        publicidad = new Dialog(this);
        publicidad.setContentView(R.layout.dialog_publicidad);
        publicidad.setCancelable(false);
        publicidad.setCanceledOnTouchOutside(false);
        publicidad2 = publicidad.findViewById(R.id.ImagePubli);
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
}