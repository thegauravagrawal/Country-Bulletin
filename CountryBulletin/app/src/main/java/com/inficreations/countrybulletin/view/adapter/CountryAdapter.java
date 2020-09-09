package com.inficreations.countrybulletin.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inficreations.countrybulletin.R;
import com.inficreations.countrybulletin.model.CountryModel;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private Context context;
    private ArrayList<CountryModel> countryModelArrayList;
    private CountryOnClickListener countryOnClickListener;

    public CountryAdapter(Context context, ArrayList<CountryModel> countryModelArrayList, CountryOnClickListener countryOnClickListener) {
        this.context = context;
        this.countryModelArrayList = countryModelArrayList;
        this.countryOnClickListener = countryOnClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate the item Layout
        View view = LayoutInflater.from(context)
                .inflate(R.layout.country_list_item, parent, false);

        // pass the view to View Holder and return it
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryModel countryModel = countryModelArrayList.get(position);

        Glide.with(context).load(countryModel.getCountryFlag())
                .placeholder(R.drawable.ic_launcher_foreground).into(holder.countryFlag);
        holder.countryName.setText(countryModel.getCountryName());
    }

    @Override
    public int getItemCount() {
        return countryModelArrayList.size();
    }

    public interface CountryOnClickListener {
        void onItemClick(int position);
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView countryFlag;
        TextView countryName;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            countryFlag = itemView.findViewById(R.id.country_flag);
            countryName = itemView.findViewById(R.id.country_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (countryOnClickListener != null
                            && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        countryOnClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
