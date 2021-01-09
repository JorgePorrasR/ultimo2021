package mx.com.systemjorge.mascotasapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mx.com.systemjorge.mascotasapp.NoticiasVo;
import mx.com.systemjorge.mascotasapp.R;


public class RecyclerNoticias extends RecyclerView.Adapter<RecyclerNoticias.ViewHolder> {

    //ArrayList<PerdidoVo> listaPerdidos;
    LayoutInflater inflater;
    List<NoticiasVo> model;
    private Dialog mDialogImagen;
    private Dialog mDialogImagen2;
    private ImageView mImageDialog;
    private ImageView mImageDialog2;
    public RecyclerNoticias(Context context, List<NoticiasVo> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerNoticias.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_noticias, parent, false);
        // return new ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
      mDialogImagen = new Dialog(parent.getContext());
       mDialogImagen.setContentView(R.layout.dialog_foto);
       mDialogImagen2 = new Dialog(parent.getContext());
       mDialogImagen2.setContentView(R.layout.dialog_foto);
       mImageDialog = mDialogImagen2.findViewById(R.id.bigFoto);
       mImageDialog = mDialogImagen.findViewById(R.id.bigFoto);
       mDialogImagen2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       mDialogImagen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return new RecyclerNoticias.ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull RecyclerNoticias.ViewHolder holder, final int position) {

        String imagen = model.get(position).getUrl();
        String imagen2 = model.get(position).getUrl2();
        String encabezado = model.get(position).getEncabezado();
        String seccion = model.get(position).getSeccion();
        String fecha = model.get(position).getFecha();
//        Picasso.get().load(imagen).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
//                .fit()
//                .centerCrop()
//                .into(holder.image);
        holder.txtFecha.setText(fecha);
        holder.txtEncabezado.setText(encabezado);
        holder.txtSeccion.setText(seccion);
        holder.asignarDatosItem(model.get(position));
        holder.asignarDatosItem2(model.get(position));
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imagen = model.get(position).getUrl2();
                showBigImage(imagen, view);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imagen = model.get(position).getUrl();
                showBigImage(imagen, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private ImageView image;
        private ImageView image2;
        private TextView txtEncabezado;
        private TextView txtFecha;
        private TextView txtSeccion;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            image = itemView.findViewById(R.id.idFotico);
            image2 = itemView.findViewById(R.id.idFotico2);
            txtEncabezado = itemView.findViewById(R.id.idEncabezado);
            txtSeccion = itemView.findViewById(R.id.idSeccion);
            txtFecha = itemView.findViewById(R.id.fecha);
        }
       public void asignarDatosItem(NoticiasVo noticiasVo) {

            Glide
                   .with(context)
                    .load(noticiasVo.getUrl())
                    .circleCrop()
                   //.placeholder(R.drawable.loading_spinner)
                  .into(image);


        }
        public void asignarDatosItem2(NoticiasVo noticiasVo) {

            Glide
                    .with(context)
                    .load(noticiasVo.getUrl2())
                    .circleCrop()
                    //.placeholder(R.drawable.loading_spinner)
                    .into(image2);


        }
    }
    private void showBigImage(String imagen, View v) {
        Glide
                .with(v)
                .load(imagen)
                .into(mImageDialog);

        mDialogImagen.show();

    }
    private void showBigImage2(String imagen2, View v) {
        Glide
                .with(v)
                .load(imagen2)
                .into(mImageDialog2);

        mDialogImagen2.show();

    }


}

