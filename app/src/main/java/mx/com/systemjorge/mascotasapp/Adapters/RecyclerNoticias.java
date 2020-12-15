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

    private ImageView mImageDialog;

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
       mImageDialog = mDialogImagen.findViewById(R.id.bigFoto);
       mDialogImagen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return new RecyclerNoticias.ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull RecyclerNoticias.ViewHolder holder, int position) {

        String imagen = model.get(position).getUrl();
        String encabezado = model.get(position).getEncabezado();
        String seccion = model.get(position).getSeccion();

//        Picasso.get().load(imagen).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
//                .fit()
//                .centerCrop()
//                .into(holder.image);


        holder.txtEncabezado.setText(encabezado);
        holder.txtSeccion.setText(seccion);
        holder.asignarDatosItem(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private ImageView image;
        private TextView txtEncabezado;
        private TextView txtSeccion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           itemView.setOnClickListener(new View.OnClickListener() {
              @Override
                public void onClick(View v) {
                    String imagen = model.get(getAdapterPosition()).getUrl();
                    showBigImage(imagen, v);
               }
            });

            context = itemView.getContext();
            image = itemView.findViewById(R.id.idFotico);
            txtEncabezado = itemView.findViewById(R.id.idEncabezado);
            txtSeccion = itemView.findViewById(R.id.idSeccion);

        }
       public void asignarDatosItem(NoticiasVo noticiasVo) {

            Glide
                   .with(context)
                    .load(noticiasVo.getUrl())
                    .circleCrop()
                   //.placeholder(R.drawable.loading_spinner)
                  .into(image);


        }
    }
    private void showBigImage(String imagen, View v) {
        Glide
                .with(v)
                .load(imagen)
                .circleCrop()
                .into(mImageDialog);

        mDialogImagen.show();

    }


}

