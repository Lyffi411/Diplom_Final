package com.example.diplom_final.ui.Ypragnenia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplom_final.R;
import com.example.diplom_final.ui.Ypragnenia.YpragneniaAdapter;
import com.example.diplom_final.ui.Ypragnenia.YpragneniaItem;
import java.util.ArrayList;
import java.util.List;

public class YpragneniaAdapter extends RecyclerView.Adapter<YpragneniaAdapter.YpragneniaViewHolder> {
    private List<YpragneniaItem> items;
    private List<YpragneniaItem> filteredItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public YpragneniaAdapter(List<YpragneniaItem> items, OnItemClickListener listener) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        this.listener = listener;
    }

    @NonNull
    @Override
    public YpragneniaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ypragnenia, parent, false);
        return new YpragneniaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YpragneniaViewHolder holder, int position) {
        YpragneniaItem currentItem = filteredItems.get(position);
        holder.imageView.setImageResource(currentItem.getImageResourceId());
        holder.titleTextView.setText(currentItem.getTitle());
        holder.descriptionTextView.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public void filter(String text) {
        filteredItems.clear();
        if (text.isEmpty()) {
            filteredItems.addAll(items);
        } else {
            text = text.toLowerCase();
            for (YpragneniaItem item : items) {
                if (item.getTitle().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text)) {
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<YpragneniaItem> getFilteredItems() {
        return filteredItems;
    }

    class YpragneniaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        YpragneniaViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewYpragnenia);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
} 