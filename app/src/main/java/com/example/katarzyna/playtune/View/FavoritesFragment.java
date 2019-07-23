package com.example.katarzyna.playtune.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Adapters.MyAdapter;
import com.example.katarzyna.playtune.Adapters.MyAdapterCart;
import com.example.katarzyna.playtune.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    public List<DiscModel> discs = new ArrayList<>();
    public List<DiscModel> discs1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_of_discs_fragment_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);

        discs = MyAdapter.getDiscsToFavorites();
        discs1 = MyAdapterCart.getDiscsToFavorites();
        discs.addAll(discs1);

        final MyAdapter myAdapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        myAdapter.setDiscs(discs);

        return view;
    }
}
