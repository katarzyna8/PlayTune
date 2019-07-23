package com.example.katarzyna.playtune.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.katarzyna.playtune.Model.DiscModel;
import com.example.katarzyna.playtune.Adapters.MyAdapter;
import com.example.katarzyna.playtune.R;
import com.example.katarzyna.playtune.ViewModel.DiscViewModel;

import java.util.List;

public class ListOfDiscsFragment extends Fragment {

    private DiscViewModel discViewModel;
    public static boolean isGrid;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public ListOfDiscsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_of_discs_fragment_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_id);
        setHasOptionsMenu(true);
        isGrid = true;

        myAdapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        discViewModel = ViewModelProviders.of(this).get(DiscViewModel.class);
        discViewModel.getAllDisc().observe(this, new Observer<List<DiscModel>>() {
            @Override
            public void onChanged(@Nullable List<DiscModel> discModels) {
                myAdapter.setDiscs(discModels);
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.changeView:
                isGrid = !isGrid;
                recyclerView.setLayoutManager(isGrid ? new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) : new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(myAdapter);
                item.setIcon(isGrid ? R.drawable.listview : R.drawable.grid);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
