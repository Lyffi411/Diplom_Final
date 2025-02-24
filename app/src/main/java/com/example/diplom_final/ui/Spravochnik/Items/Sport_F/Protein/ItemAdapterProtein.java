package com.example.diplom_final.ui.Spravochnik.Items.Sport_F.Protein;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diplom_final.R;

import java.util.List;

public class ItemAdapterProtein extends RecyclerView.Adapter<ItemAdapterProtein.ViewHolder> {

    private List<ItemProtein> items;
    private com.example.diplom_final.ui.Spravochnik.ItemAdapterSpravo.OnItemClickListener listener; // Интерфейс для кликов

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ItemAdapterProtein(List<ItemProtein> items, com.example.diplom_final.ui.Spravochnik.ItemAdapterSpravo.OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pharm_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemProtein item = items.get(position);
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




