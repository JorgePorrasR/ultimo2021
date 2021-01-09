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

import mx.com.systemjorge.mascotasapp.FechaVo;
import mx.com.systemjorge.mascotasapp.NoticiasVo;
import mx.com.systemjorge.mascotasapp.R;


public class RecyclerLugares extends RecyclerView.Adapter<RecyclerLugares.ViewHolder> {

    //ArrayList<PerdidoVo> listaPerdidos;
    LayoutInflater inflater;
    List<FechaVo> model;
    public RecyclerLugares(Context context, List<FechaVo> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerLugares.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_primer, parent, false);
        // return new ViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);

        return new RecyclerLugares.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerLugares.ViewHolder holder, final int position) {
        String lugar = model.get(position).getFechita();

        holder.txtFechita.setText(lugar);
    }



    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private TextView txtFechita;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

        }
    }

}