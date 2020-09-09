package com.inficreations.countrybulletin.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inficreations.countrybulletin.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<String> data;
    private CategoryOnClickListener categoryOnClickListener;

    public CategoryAdapter(Context context, List<String> data, CategoryOnClickListener categoryOnClickListener) {
        this.context = context;
        this.data = data;
        this.categoryOnClickListener = categoryOnClickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // inflate the item Layout
        View view = LayoutInflater.from(context)
                .inflate(R.layout.category_list_item, parent, false);

        // pass the view to View Holder and return it
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.category.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface CategoryOnClickListener {
        void onItemClick(int position);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        Button category;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category_item);

            category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    System.out.println("categoryOnClickListener "+categoryOnClickListener +" "+getAdapterPosition());
                    if (categoryOnClickListener != null
                            && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        categoryOnClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}

