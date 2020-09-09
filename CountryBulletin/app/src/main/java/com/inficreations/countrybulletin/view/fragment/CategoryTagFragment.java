package com.inficreations.countrybulletin.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.inficreations.countrybulletin.R;
import com.inficreations.countrybulletin.view.activity.MainActivity;
import com.inficreations.countrybulletin.view.adapter.CategoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryTagFragment extends BottomSheetDialogFragment implements CategoryAdapter.CategoryOnClickListener {
    private List<String> data;

    public CategoryTagFragment(List<String> data) {
        this.data = data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_tag, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.category_recycler_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayout);

        CategoryAdapter categoryAdapter = new CategoryAdapter
                (getActivity(), data, this);

        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        view.findViewById(R.id.highlights).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.categoryName = "highlights";
                startActivity();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {
        System.out.println("onItemClick " + position);
        MainActivity.categoryName = data.get(position);

        startActivity(new Intent(getActivity(), MainActivity.class));
        this.dismiss();
    }
    private void startActivity() {
        MainActivity.isComingFromSearch = false;
        startActivity(new Intent(getActivity(), MainActivity.class));
        this.dismiss();
    }

}
