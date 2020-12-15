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

import mx.com.systemjorge.mascotasapp.PerdidoVo;
import mx.com.systemjorge.mascotasapp.R;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    //ArrayList<PerdidoVo> listaPerdidos;
    LayoutInflater inflater;
    List<PerdidoVo> model;
    private Dialog mDialogImagen;

    private ImageView mImageDialog;

    public RecyclerAdapter(Context context, List<PerdidoVo> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.iem_list_perdidos, parent, false);
       // return new ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
        mDialogImagen = new Dialog(parent.getContext());
        mDialogImagen.setContentView(R.layout.dialog_imagen);
        mImageDialog = mDialogImagen.findViewById(R.id.bigImage);
        mDialogImagen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return new RecyclerAdapter.ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        String imagen = model.get(position).getUrl();
        String name = model.get(position).getNombre();
        String description = model.get(position).getDescripcion();

//        Picasso.get().load(imagen).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
//                .fit()
//                .centerCrop()
//                .into(holder.image);


        holder.txtName.setText(name);
        holder.txtDescription.setText(description);
        holder.asignarDatosItem(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private ImageView image;
        private TextView txtName;
        private TextView txtDescription;

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
            image = itemView.findViewById(R.id.idImagen);
            txtName = itemView.findViewById(R.id.idNombre);
            txtDescription = itemView.findViewById(R.id.idInfo);

        }
        public void asignarDatosItem(PerdidoVo perdidoVo) {

            Glide
                    .with(context)
                    .load(perdidoVo.getUrl())
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
