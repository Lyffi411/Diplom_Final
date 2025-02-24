package com.example.diplom_final.ui.Spravochnik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;
import java.util.List;

public class ItemAdapterSpravo extends RecyclerView.Adapter<ItemAdapterSpravo.ViewHolder> {

    private List<ItemSpravo> items;
    private OnItemClickListener listener; // Интерфейс для кликов

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ItemAdapterSpravo(List<ItemSpravo> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spravohnick_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemSpravo item = items.get(position);
        holder.imageView.setImageResource(item.getImageRes());
        holder.textView.setText(item.getTitle());

        // Обрабатываем клик
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.itemText);
        }
    }
}