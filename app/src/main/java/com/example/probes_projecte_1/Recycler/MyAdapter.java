package com.example.probes_projecte_1.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probes_projecte_1.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<YourDataModel> data;
    private Context context; // Agregado el contexto

    public MyAdapter(List<YourDataModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YourDataModel currentItem = data.get(position);
        holder.ProductID.setText(String.valueOf(currentItem.getId()));
        holder.ProductName.setText(currentItem.getNom());
        holder.PorductDefinition.setText(currentItem.getDefinicio());
        holder.ProductPrice.setText(String.valueOf(currentItem.getPreu()));
        holder.ProductCategori.setText(String.valueOf(currentItem.getCategoria_id()));
        holder.ProductQuantity.setText(String.valueOf(currentItem.getQuantitat()));
        Picasso.get().load(currentItem.getImageUrl()).into(holder.ProductImage); // L'imatge la pasem per picasso
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ProductID, ProductName, PorductDefinition, ProductPrice, ProductCategori, ProductQuantity;
        public ImageView ProductImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ProductID = itemView.findViewById(R.id.id);
            ProductName = itemView.findViewById(R.id.PorductName);
            PorductDefinition = itemView.findViewById(R.id.ProductDefinition);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            ProductCategori = itemView.findViewById(R.id.ProductCategori);
            ProductQuantity = itemView.findViewById(R.id.ProductQuantity);
            ProductImage = itemView.findViewById(R.id.ProductImage);
        }
    }
}

